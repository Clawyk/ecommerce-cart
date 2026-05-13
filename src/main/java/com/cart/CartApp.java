package com.cart;

import com.cart.facade.CheckoutFacade;
import com.cart.strategy.BulkPricing;
import com.cart.strategy.DiscountedPricing;
import com.cart.strategy.StandardPricing;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CartApp extends Application {

    private ShoppingCart cart = new ShoppingCart();
    private CheckoutFacade checkoutFacade = new CheckoutFacade(cart);
    private ObservableList<Product> cartItems = FXCollections.observableArrayList();
    private Label totalLabel = new Label("Toplam: 0.00 TL");
    private TextArea logArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("E-Ticaret Sepet Sistemi — Tasarım Örüntüleri Ödevi");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e2e;");

        root.setTop(buildHeader());
        root.setLeft(buildProductPanel());
        root.setCenter(buildCartPanel());
        root.setRight(buildStrategyPanel());
        root.setBottom(buildLogPanel());

        Scene scene = new Scene(root, 1100, 700);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private HBox buildHeader() {
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #313244;");
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("🛒  E-Ticaret Sepet Sistemi");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setTextFill(Color.web("#cdd6f4"));

        Label subtitle = new Label("  —  Tasarım Örüntüleri: Factory Method · Decorator · Facade · Strategy · Observer");
        subtitle.setFont(Font.font("Arial", 12));
        subtitle.setTextFill(Color.web("#6c7086"));

        header.getChildren().addAll(title, subtitle);
        return header;
    }

    private VBox buildProductPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: #181825;");
        panel.setPrefWidth(250);

        Label title = styledLabel("Ürün Ekle", "#cba6f7", 14);

        TextField nameField = new TextField();
        nameField.setPromptText("Ürün adı");
        styleTextField(nameField);

        TextField priceField = new TextField();
        priceField.setPromptText("Fiyat (TL)");
        styleTextField(priceField);

        TextField qtyField = new TextField();
        qtyField.setPromptText("Adet");
        styleTextField(qtyField);

        Button addBtn = styledButton("➕  Sepete Ekle", "#a6e3a1");
        addBtn.setMaxWidth(Double.MAX_VALUE);

        addBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String priceText = priceField.getText().trim();
            String qtyText = qtyField.getText().trim();

            if (name.isEmpty() || priceText.isEmpty() || qtyText.isEmpty()) {
                log("⚠ Tüm alanları doldurun!");
                return;
            }
            try {
                double price = Double.parseDouble(priceText);
                int qty = Integer.parseInt(qtyText);
                Product p = new Product(name, price, qty);
                cart.addItem(p);
                cartItems.add(p);
                nameField.clear();
                priceField.clear();
                qtyField.clear();
                updateTotal();
                log(" " + name + " sepete eklendi.");
            } catch (NumberFormatException ex) {
                log(" Geçersiz fiyat veya adet!");
            }
        });

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #313244;");

        Label removeTitle = styledLabel("Ürün Çıkar", "#cba6f7", 14);
        TextField removeField = new TextField();
        removeField.setPromptText("Ürün adı");
        styleTextField(removeField);

        Button removeBtn = styledButton("➖  Sepetten Çıkar", "#f38ba8");
        removeBtn.setMaxWidth(Double.MAX_VALUE);

        removeBtn.setOnAction(e -> {
            String name = removeField.getText().trim();
            if (name.isEmpty()) { log("⚠ Ürün adı girin!"); return; }
            cart.removeItem(name);
            cartItems.removeIf(p -> p.name.equals(name));
            removeField.clear();
            updateTotal();
            log("🗑 " + name + " sepetten çıkarıldı.");
        });

        panel.getChildren().addAll(
                title,
                styledLabel("Ad", "#a6adc8", 11), nameField,
                styledLabel("Fiyat", "#a6adc8", 11), priceField,
                styledLabel("Adet", "#a6adc8", 11), qtyField,
                addBtn, sep,
                removeTitle,
                styledLabel("Ad", "#a6adc8", 11), removeField,
                removeBtn
        );
        return panel;
    }

    private VBox buildCartPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: #1e1e2e;");

        Label title = styledLabel("Sepet", "#89dceb", 14);

        TableView<Product> table = new TableView<>(cartItems);
        table.setStyle("-fx-background-color: #313244; -fx-text-fill: #cdd6f4;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Product, String> nameCol = new TableColumn<>("Ürün");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setStyle("-fx-text-fill: #cdd6f4;");

        TableColumn<Product, Double> priceCol = new TableColumn<>("Fiyat (TL)");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> qtyCol = new TableColumn<>("Adet");
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Product, Double> subtotalCol = new TableColumn<>("Ara Toplam");
        subtotalCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow().getItem() == null) { setText(null); return; }
                Product p = (Product) getTableRow().getItem();
                setText(String.format("%.2f TL", p.price * p.quantity));
            }
        });

        table.getColumns().addAll(nameCol, priceCol, qtyCol, subtotalCol);
        VBox.setVgrow(table, Priority.ALWAYS);

        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        totalLabel.setTextFill(Color.web("#f9e2af"));

        panel.getChildren().addAll(title, table, totalLabel);
        return panel;
    }

    private VBox buildStrategyPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: #181825;");
        panel.setPrefWidth(220);

        Label stratTitle = styledLabel("Fiyatlandırma Stratejisi", "#cba6f7", 14);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbStandard = styledRadio("Standart Fiyat", group);
        RadioButton rbDiscount = styledRadio("Yüzde İndirim", group);
        RadioButton rbBulk = styledRadio("Toplu Alım (%15)", group);
        rbStandard.setSelected(true);

        TextField discountField = new TextField("10");
        discountField.setPromptText("İndirim %");
        styleTextField(discountField);
        discountField.setVisible(false);

        rbDiscount.setOnAction(e -> discountField.setVisible(true));
        rbStandard.setOnAction(e -> discountField.setVisible(false));
        rbBulk.setOnAction(e -> discountField.setVisible(false));

        Button applyBtn = styledButton("  Stratejiyi Uygula", "#89b4fa");
        applyBtn.setMaxWidth(Double.MAX_VALUE);

        applyBtn.setOnAction(e -> {
            if (rbStandard.isSelected()) {
                cart.setPricingStrategy(new StandardPricing());
                log(" Standart fiyatlandırma seçildi.");
            } else if (rbDiscount.isSelected()) {
                try {
                    double pct = Double.parseDouble(discountField.getText().trim());
                    cart.setPricingStrategy(new DiscountedPricing(pct));
                    log(" %" + pct + " indirimli fiyatlandırma seçildi.");
                } catch (NumberFormatException ex) {
                    log("⚠ Geçersiz indirim oranı!"); return;
                }
            } else {
                cart.setPricingStrategy(new BulkPricing());
                log("📌 Toplu alım fiyatlandırması seçildi.");
            }
            updateTotal();
        });

        Separator sep = new Separator();
        Label payTitle = styledLabel("Ödeme Yöntemi", "#cba6f7", 14);

        ComboBox<String> paymentBox = new ComboBox<>();
        paymentBox.getItems().addAll("Kredi Kartı", "Nakit", "PayPal");
        paymentBox.setValue("Kredi Kartı");
        paymentBox.setMaxWidth(Double.MAX_VALUE);
        paymentBox.setStyle("-fx-background-color: #313244; -fx-text-fill: #cdd6f4;");

        Button checkoutBtn = styledButton("💳  Ödeme Yap", "#fab387");
        checkoutBtn.setMaxWidth(Double.MAX_VALUE);

        checkoutBtn.setOnAction(e -> {
            if (cartItems.isEmpty()) { log("⚠ Sepet boş!"); return; }
            String method = switch (paymentBox.getValue()) {
                case "Nakit" -> "CASH";
                case "PayPal" -> "PAYPAL";
                default -> "CREDIT_CARD";
            };
            checkoutFacade.checkout(method);
            cartItems.clear();
            updateTotal();
            log("🎉 Ödeme tamamlandı!");
        });

        panel.getChildren().addAll(
                stratTitle, rbStandard, rbDiscount, discountField, rbBulk,
                applyBtn, sep, payTitle, paymentBox, checkoutBtn
        );
        return panel;
    }

    private VBox buildLogPanel() {
        VBox panel = new VBox(5);
        panel.setPadding(new Insets(10, 20, 10, 20));
        panel.setStyle("-fx-background-color: #11111b;");
        panel.setPrefHeight(130);

        Label title = styledLabel("Sistem Logu", "#6c7086", 11);
        logArea.setEditable(false);
        logArea.setStyle("-fx-background-color: #11111b; -fx-text-fill: #a6e3a1; "
                + "-fx-font-family: monospace; -fx-font-size: 11;");
        logArea.setPrefHeight(100);
        panel.getChildren().addAll(title, logArea);
        return panel;
    }

    private void updateTotal() {
        if (cartItems.isEmpty()) {
            totalLabel.setText("Toplam: 0.00 TL");
            return;
        }
        double total = cart.calculateTotal();
        totalLabel.setText(String.format("Toplam: %.2f TL", total));
    }

    private void log(String msg) {
        logArea.appendText(msg + "\n");
    }

    private Label styledLabel(String text, String color, int size) {
        Label l = new Label(text);
        l.setTextFill(Color.web(color));
        l.setFont(Font.font("Arial", FontWeight.BOLD, size));
        return l;
    }

    private void styleTextField(TextField tf) {
        tf.setStyle("-fx-background-color: #313244; -fx-text-fill: #cdd6f4; "
                + "-fx-prompt-text-fill: #6c7086; -fx-border-color: #45475a; "
                + "-fx-border-radius: 4; -fx-background-radius: 4;");
    }

    private Button styledButton(String text, String color) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: #1e1e2e; "
                + "-fx-font-weight: bold; -fx-background-radius: 6; -fx-cursor: hand;");
        return btn;
    }

    private RadioButton styledRadio(String text, ToggleGroup group) {
        RadioButton rb = new RadioButton(text);
        rb.setToggleGroup(group);
        rb.setTextFill(Color.web("#cdd6f4"));
        return rb;
    }
}