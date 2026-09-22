package ra.business;

import ra.entity.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ProductBusiness {

    private ArrayList<Product> products = new ArrayList<>();

    // Thêm sản phẩm
    public void addProduct(Scanner scanner) {

        Product product = new Product();

        while (true) {
            product.inputData(scanner);

            // Kiểm tra tên sản phẩm không trùng
            boolean exists = false;

            for (Product p : products) {
                if (p.getProductName().equalsIgnoreCase(product.getProductName())) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                System.out.println("Tên sản phẩm đã tồn tại!");
                System.out.println("Vui lòng nhập lại.");
            } else {
                products.add(product);
                System.out.println("Thêm sản phẩm thành công!");
                break;
            }
        }
    }

    // Hiển thị danh sách
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm đang trống!");
            return;
        }

        System.out.println("========== DANH SÁCH SẢN PHẨM ==========");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Tìm sản phẩm theo ID
    private Product findById(int productId) {

        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }

        return null;
    }

    // Cập nhật sản phẩm
    public void updateProduct(Scanner scanner) {

        System.out.print("Nhập mã sản phẩm cần cập nhật: ");

        int productId;

        try {
            productId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm phải là số nguyên!");
            return;
        }

        Product product = findById(productId);

        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm có mã: " + productId);
            return;
        }

        System.out.println("Sản phẩm hiện tại:");
        System.out.println(product);

        // Cập nhật tên
        while (true) {
            System.out.print("Nhập tên mới: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println("Tên phải từ 10-50 ký tự!");
                continue;
            }

            boolean duplicate = false;

            for (Product p : products) {
                if (p != product &&
                        p.getProductName().equalsIgnoreCase(name)) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("Tên sản phẩm đã tồn tại!");
            } else {
                product.setProductName(name);
                break;
            }
        }

        // Cập nhật giá
        while (true) {
            try {
                System.out.print("Nhập giá mới: ");
                float price = Float.parseFloat(scanner.nextLine());

                if (price <= 0) {
                    System.out.println("Giá phải > 0!");
                } else {
                    product.setPrice(price);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Giá phải là số!");
            }
        }

        // Cập nhật danh mục
        while (true) {
            System.out.print("Nhập danh mục mới: ");
            String category = scanner.nextLine().trim();

            if (category.length() > 200) {
                System.out.println("Danh mục không được quá 200 ký tự!");
            } else {
                product.setCategory(category);
                break;
            }
        }

        // Cập nhật số lượng
        while (true) {
            try {
                System.out.print("Nhập số lượng mới: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity < 0) {
                    System.out.println("Số lượng phải >= 0!");
                } else {
                    product.setQuantity(quantity);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên!");
            }
        }

        System.out.println("Cập nhật sản phẩm thành công!");
    }

    // Xóa sản phẩm
    public void deleteProduct(Scanner scanner) {

        System.out.print("Nhập mã sản phẩm cần xóa: ");

        int productId;

        try {
            productId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm phải là số nguyên!");
            return;
        }

        Product product = findById(productId);

        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm!");
            return;
        }

        products.remove(product);

        System.out.println("Xóa sản phẩm thành công!");
    }

    // Tìm kiếm theo tên
    public void searchByName(Scanner scanner) {

        System.out.print("Nhập tên hoặc từ khóa cần tìm: ");
        String keyword = scanner.nextLine().toLowerCase().trim();

        boolean found = false;

        for (Product product : products) {

            if (product.getProductName()
                    .toLowerCase()
                    .contains(keyword)) {

                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm!");
        }
    }

    // Sắp xếp giá tăng dần
    public void sortByPriceAsc() {

        products.sort(Comparator.comparing(Product::getPrice));

        System.out.println("Đã sắp xếp giá tăng dần!");
        displayProducts();
    }

    // Sắp xếp số lượng giảm dần
    public void sortByQuantityDesc() {

        products.sort(
                Comparator.comparing(Product::getQuantity).reversed()
        );

        System.out.println("Đã sắp xếp số lượng giảm dần!");
        displayProducts();
    }
}
