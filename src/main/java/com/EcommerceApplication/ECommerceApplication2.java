package com.EcommerceApplication;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Products {
    String name, company, description, category, subCategory;
    double price;

    Products(String n, String c, double p, String d, String cat, String sub) {
        name=n; company=c; price=p; description=d;
        category=cat; subCategory=sub;
    }

    public String toString() {
        return name+" - Rs."+price;
    }
}

public class ECommerceApplication2 extends JFrame implements ActionListener {

    JComboBox<String> categoryBox, subCategoryBox;
    JComboBox<Products> productBox;
    JTextArea productDetails;
    JButton addButton, cartButton, checkoutButton, payButton;
    ArrayList<Products> cart = new ArrayList<>();

    String[] categories={
        "Electronics","Clothing","Furniture","Groceries","Home Decor"
    };

    String[][] subCategories={
        {"Mobiles","Laptops","Headphones","Speakers","Cameras"},
        {"Men Wear","Women Wear","Kids Wear","Footwear","Accessories"},
        {"Chairs","Tables","Beds","Sofas","Cabinets"},
        {"Fruits","Vegetables","Snacks","Beverages","Dairy"},
        {"Lighting","Wall Art","Vases","Rugs","Clocks"}
    };

    String[][][] names={
        {
            {"iPhone 15","Samsung Galaxy S24","OnePlus 12","Google Pixel 8","Xiaomi 14",
             "Vivo V30","Oppo Reno 11","Realme GT 6","Nothing Phone 2","Motorola Edge 50"},
            {"HP Pavilion 15","Dell Inspiron 15","Lenovo IdeaPad Slim 5","ASUS VivoBook 15",
             "Acer Aspire 5","Apple MacBook Air","HP Envy x360","Dell XPS 13",
             "Lenovo ThinkPad E14","ASUS ROG Strix G16"},
            {"Sony WH-1000XM5","Bose QuietComfort","JBL Tune 770NC","Boat Rockerz 550",
             "Sennheiser HD 450BT","Apple AirPods Max","OnePlus Buds Pro",
             "Samsung Galaxy Buds 2","Realme Buds Air 5","Noise Air Buds"},
            {"JBL Flip 6","Sony SRS-XB23","Bose SoundLink","Boat Stone 1200",
             "Marshall Emberton","JBL Charge 5","Sony SRS-XE300",
             "Portronics SoundDrum","Zebronics Zeb-Sound","Infinity Glide 500"},
            {"Canon EOS R50","Nikon Z50","Sony Alpha A6400","Canon EOS 90D",
             "Nikon D7500","Sony Alpha A7 III","Fujifilm X-S20",
             "Panasonic Lumix G95","GoPro Hero 12","DJI Osmo Action 4"}
        },

        {
            {"Cotton Formal Shirt","Slim Fit Jeans","Casual Polo Shirt","Linen Kurta",
             "Denim Jacket","Formal Trousers","Printed Casual Shirt","Cotton T-Shirt",
             "Traditional Sherwani","Men's Blazer"},
            {"Cotton Saree","Designer Saree","Anarkali Dress","Salwar Suit","Lehenga Choli",
             "Kurti","Western Maxi Dress","Palazzo Set","Chiffon Dupatta","Party Wear Gown"},
            {"Kids Cotton T-Shirt","Denim Dungaree","Kids Party Dress","Boys Casual Shirt",
             "Girls Frock","Kids Jeans","School Uniform","Kids Kurta Pajama",
             "Printed Shorts","Kids Winter Jacket"},
            {"Sports Running Shoes","Leather Formal Shoes","Casual Sneakers",
             "Women's Sandals","Men's Loafers","Flip Flops","High Heel Sandals",
             "Walking Shoes","Traditional Juttis","Kids School Shoes"},
            {"Leather Wallet","Classic Wrist Watch","Aviator Sunglasses","Leather Belt",
             "Gold Plated Bracelet","Fashion Handbag","Silk Scarf","Baseball Cap",
             "Travel Backpack","Fashion Earrings"}
        },

        {
            {"Wooden Dining Chair","Office Ergonomic Chair","Recliner Chair","Rocking Chair",
             "Plastic Dining Chair","Leather Executive Chair","Accent Chair",
             "Folding Chair","Bar Stool","Gaming Chair"},
            {"Wooden Dining Table","Glass Coffee Table","Study Table","Office Work Table",
             "Round Dining Table","Computer Table","Bedside Table","Console Table",
             "Folding Table","Marble Top Table"},
            {"Queen Size Wooden Bed","King Size Wooden Bed","Hydraulic Storage Bed",
             "Single Bed","Metal Frame Bed","Upholstered Bed","Platform Bed",
             "Kids Single Bed","Teak Wood Bed","Modern Storage Bed"},
            {"Three Seater Sofa","L-Shaped Sofa","Leather Sofa","Fabric Sofa",
             "Recliner Sofa","Sectional Sofa","Loveseat Sofa","Velvet Sofa",
             "Corner Sofa","Convertible Sofa Bed"},
            {"Wooden Storage Cabinet","Kitchen Cabinet","Office File Cabinet",
             "Glass Display Cabinet","Bedroom Wardrobe","TV Storage Cabinet",
             "Shoe Cabinet","Bookshelf Cabinet","Bathroom Cabinet","Multi Purpose Cabinet"}
        },

        {
            {"Fresh Apples","Fresh Bananas","Fresh Mangoes","Fresh Oranges","Fresh Grapes",
             "Fresh Watermelon","Fresh Papaya","Fresh Pineapple","Fresh Pomegranate",
             "Fresh Guava"},
            {"Fresh Tomatoes","Fresh Potatoes","Fresh Onions","Fresh Carrots","Fresh Spinach",
             "Fresh Cauliflower","Fresh Cabbage","Fresh Brinjal","Fresh Green Beans",
             "Fresh Green Peas"},
            {"Potato Chips","Masala Biscuits","Salted Peanuts","Chocolate Cookies",
             "Cheese Crackers","Spicy Mixture","Banana Chips","Popcorn","Nacho Chips",
             "Roasted Cashews"},
            {"Orange Juice","Mango Juice","Apple Juice","Cold Coffee","Green Tea",
             "Black Tea","Lemon Drink","Coconut Water","Mineral Water",
             "Chocolate Milkshake"},
            {"Fresh Milk","Full Cream Milk","Paneer","Cheddar Cheese","Butter","Curd",
             "Greek Yogurt","Fresh Cream","Flavored Yogurt","Milk Powder"}
        },

        {
            {"LED Ceiling Light","Table Lamp","Floor Lamp","Wall Sconce Light",
             "Pendant Light","Chandelier","Bedside Lamp","Decorative String Lights",
             "Smart LED Bulb","Vintage Lantern"},
            {"Abstract Wall Painting","Nature Landscape Painting","Floral Wall Art",
             "Modern Canvas Art","Traditional Indian Painting","Mountain Wall Art",
             "Ocean Artwork","Geometric Wall Art","Mandala Wall Art",
             "Family Photo Frame"},
            {"Ceramic Flower Vase","Glass Flower Vase","Metal Decorative Vase",
             "Terracotta Vase","Crystal Vase","Wooden Vase","Marble Vase",
             "Hand Painted Vase","Modern Designer Vase","Mini Table Vase"},
            {"Persian Area Rug","Cotton Floor Rug","Modern Abstract Rug",
             "Shaggy Carpet Rug","Round Floor Rug","Kids Cartoon Rug",
             "Traditional Indian Rug","Bedroom Runner Rug","Living Room Rug",
             "Outdoor Door Mat"},
            {"Wooden Wall Clock","Digital Wall Clock","Analog Wall Clock",
             "Modern Designer Clock","Vintage Clock","Pendulum Clock",
             "LED Digital Clock","Metal Wall Clock","Roman Number Clock",
             "Table Alarm Clock"}
        }
    };

    Products[][][] products=new Products[5][5][10];

    public ECommerceApplication2() {
        createProducts();

        setTitle("Simple E-Commerce Application");
        setSize(900,600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel top=new JPanel();

        categoryBox=new JComboBox<>(categories);
        subCategoryBox=new JComboBox<>(subCategories[0]);
        productBox=new JComboBox<>(products[0][0]);

        top.add(new JLabel("Category:"));
        top.add(categoryBox);
        top.add(new JLabel("Sub Category:"));
        top.add(subCategoryBox);
        top.add(new JLabel("Product:"));
        top.add(productBox);

        add(top,BorderLayout.NORTH);

        productDetails=new JTextArea();
        productDetails.setEditable(false);
        productDetails.setFont(new Font("Arial",Font.PLAIN,16));
        productDetails.setLineWrap(true);
        productDetails.setWrapStyleWord(true);

        add(new JScrollPane(productDetails),BorderLayout.CENTER);

        JPanel bottom=new JPanel();

        addButton=new JButton("Add To Cart");
        cartButton=new JButton("View Cart");
        checkoutButton=new JButton("Checkout");
        payButton=new JButton("Pay Now");

        for(JButton b:new JButton[]{
            addButton,cartButton,checkoutButton,payButton
        }) {
            bottom.add(b);
            b.addActionListener(this);
        }

        add(bottom,BorderLayout.SOUTH);

        categoryBox.addActionListener(this);
        subCategoryBox.addActionListener(this);
        productBox.addActionListener(this);

        showProduct();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    void createProducts() {
        for(int i=0;i<5;i++)
            for(int j=0;j<5;j++)
                for(int k=0;k<10;k++)
                    products[i][j][k]=new Products(
                        names[i][j][k],
                        company(i,k),
                        50+(k+1)*150,
                        description(subCategories[i][j]),
                        categories[i],
                        subCategories[i][j]
                    );
    }

    String company(int c,int p) {
        if(c==0)
            return new String[]{
                "Apple","Samsung","OnePlus","Sony","Google"
            }[p%5];

        return new String[]{
            "Fashion Hub","Urban Furniture","Fresh Mart","Home Style"
        }[c-1];
    }

    String description(String s) {
        if(s.equals("Mobiles"))
            return "Latest smartphone with powerful performance, high-quality camera and long-lasting battery.";

        if(s.equals("Laptops"))
            return "High-performance laptop suitable for students, professionals and everyday computing.";

        if(s.equals("Headphones"))
            return "Premium headphones with clear sound and comfortable ear cushions.";

        if(s.equals("Speakers"))
            return "Portable speaker with powerful sound and wireless connectivity.";

        if(s.equals("Cameras"))
            return "High-quality digital camera for sharp photos and professional videos.";

        if(s.equals("Men Wear")||s.equals("Women Wear")||s.equals("Kids Wear"))
            return "Comfortable and stylish clothing made with quality fabric.";

        if(s.equals("Footwear"))
            return "Comfortable footwear with durable construction and stylish design.";

        if(s.equals("Accessories"))
            return "Stylish fashion accessory designed for everyday use.";

        if(s.equals("Chairs"))
            return "Strong and comfortable chair made with durable materials.";

        if(s.equals("Tables"))
            return "Modern and sturdy table suitable for home and office.";

        if(s.equals("Beds"))
            return "Comfortable and durable bed designed for excellent support.";

        if(s.equals("Sofas"))
            return "Premium comfortable sofa with stylish design.";

        if(s.equals("Cabinets"))
            return "Spacious storage cabinet with strong construction.";

        if(s.equals("Fruits"))
            return "Fresh and naturally sweet fruit selected for quality.";

        if(s.equals("Vegetables"))
            return "Fresh vegetables suitable for healthy everyday cooking.";

        if(s.equals("Snacks"))
            return "Crispy and delicious snack prepared with quality ingredients.";

        if(s.equals("Beverages"))
            return "Refreshing beverage with delicious taste.";

        if(s.equals("Dairy"))
            return "Fresh dairy product made from quality milk.";

        if(s.equals("Lighting"))
            return "Beautiful decorative lighting designed to brighten your room.";

        if(s.equals("Wall Art"))
            return "Beautiful artwork designed to enhance your walls.";

        if(s.equals("Vases"))
            return "Elegant decorative vase suitable for flowers.";

        if(s.equals("Rugs"))
            return "Soft and attractive rug designed for comfort and style.";

        return "Stylish decorative clock designed to display time and enhance your home.";
    }

    void showProduct() {
        Products p=(Products)productBox.getSelectedItem();

        if(p!=null)
            productDetails.setText(
                "========== PRODUCT DETAILS ==========\n\n"+
                "Product Name : "+p.name+
                "\nCategory : "+p.category+
                "\nSub Category : "+p.subCategory+
                "\nCompany : "+p.company+
                "\nPrice : Rs."+p.price+
                "\nDescription : "+p.description
            );
    }

    void load(int c,int s) {
        productBox.removeAllItems();

        for(Products p:products[c][s])
            productBox.addItem(p);

        showProduct();
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==categoryBox) {
            int c=categoryBox.getSelectedIndex();

            subCategoryBox.removeAllItems();

            for(String s:subCategories[c])
                subCategoryBox.addItem(s);

            load(c,0);

        } else if(e.getSource()==subCategoryBox) {

            int c=categoryBox.getSelectedIndex();
            int s=subCategoryBox.getSelectedIndex();

            if(c>=0&&s>=0)
                load(c,s);

        } else if(e.getSource()==productBox) {

            showProduct();

        } else if(e.getSource()==addButton) {

            addCart();

        } else if(e.getSource()==cartButton) {

            showCart();

        } else if(e.getSource()==checkoutButton) {

            checkout();

        } else if(e.getSource()==payButton) {

            payNow();
        }
    }

    void addCart() {
        Products p=(Products)productBox.getSelectedItem();

        if(p!=null) {
            cart.add(p);

            JOptionPane.showMessageDialog(
                this,
                p.name+" added to cart",
                "Cart",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    double total() {
        double t=0;

        for(Products p:cart)
            t+=p.price;

        return t;
    }

    void showCart() {
        if(cart.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Cart is empty",
                "Cart",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        StringBuilder s=new StringBuilder();

        for(int i=0;i<cart.size();i++)
            s.append(i+1)
             .append(". ")
             .append(cart.get(i).name)
             .append(" - Rs.")
             .append(cart.get(i).price)
             .append("\n");

        s.append("\nTotal Amount: Rs.").append(total());

        JTextArea a=new JTextArea(s.toString());
        a.setEditable(false);

        JOptionPane.showMessageDialog(
            this,
            new JScrollPane(a),
            "Shopping Cart",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    void checkout() {
        if(cart.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Cart is empty",
                "Checkout",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
            this,
            "Items: "+cart.size()+
            "\nTotal Amount: Rs."+total()+
            "\n\nClick Pay Now to complete payment.",
            "Checkout",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    void payNow() {

        if(cart.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Cart is empty",
                "Payment",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String name=JOptionPane.showInputDialog(
            this,
            "Enter your name:"
        );

        if(name==null||name.trim().isEmpty())
            return;

        // CARD NUMBER - MAXIMUM 16 DIGITS
        String card;

        while(true) {

            card=JOptionPane.showInputDialog(
                this,
                "Enter card number (maximum 16 digits):"
            );

            if(card==null)
                return;

            if(card.matches("\\d{1,16}"))
                break;

            JOptionPane.showMessageDialog(
                this,
                "Card number must contain only 16digits\n"+
                "and maximum 16 digits.",
                "Invalid Card Number",
                JOptionPane.ERROR_MESSAGE
            );
        }

        String amount=JOptionPane.showInputDialog(
            this,
            "Enter payment amount:\n"+
            "Total Amount: Rs."+total()
        );

        if(amount==null)
            return;

        try {

            double paid=Double.parseDouble(amount);

            if(paid<total()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Insufficient payment amount.\n"+
                    "Total Amount: Rs."+total(),
                    "Payment Failed",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            JOptionPane.showMessageDialog(
                this,
                "========== PAYMENT SUCCESSFUL ==========\n\n"+
                "Customer: "+name+
                "\nCard Number: "+card+
                "\nAmount Paid: Rs."+paid+
                "\nOrder Amount: Rs."+total()+
                "\n\nThank you for shopping!",
                "Payment Successful",
                JOptionPane.INFORMATION_MESSAGE
            );

            cart.clear();

            // CLOSE APPLICATION AFTER OK
            dispose();
            System.exit(0);

        } catch(NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid amount.",
                "Payment Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            ECommerceApplication2::new
        );
    }
}

