package ra.entity;

import java.util.Scanner;

public class Product {
    private static int nextId = 1;

    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    // Constructor không tham số
    public Product() {
        this.productId = nextId++;
    }

    // Constructor đầy đủ tham số
    public Product(int productId, String productName, float price,
                   String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;

        if (productId >= nextId) {
            nextId = productId + 1;
        }
    }

    // Getter và Setter
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {

        while (true) {
            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên sản phẩm phải từ 10-50 ký tự!");
            } else {
                this.productName = name;
                break;
            }
        }

        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                float price = Float.parseFloat(scanner.nextLine());

                if (price <= 0) {
                    System.out.println("Giá sản phẩm phải > 0!");
                } else {
                    this.price = price;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá sản phẩm phải là số!");
            }
        }

        while (true) {
            System.out.print("Nhập danh mục sản phẩm: ");
            String category = scanner.nextLine().trim();

            if (category.length() > 200) {
                System.out.println("Danh mục không được quá 200 ký tự!");
            } else {
                this.category = category;
                break;
            }
        }

        while (true) {
            try {
                System.out.print("Nhập số lượng tồn kho: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 0) {
                    System.out.println("Số lượng phải >= 0!");
                } else {
                    this.quantity = quantity;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên!");
            }
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
