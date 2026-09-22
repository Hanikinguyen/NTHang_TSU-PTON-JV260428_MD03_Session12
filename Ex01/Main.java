import ra.business.ProductBusiness;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ProductBusiness productBusiness = new ProductBusiness();

        while (true) {

            System.out.println();
            System.out.println("**************** QUẢN LÝ SẢN PHẨM ****************");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Danh sách sản phẩm");
            System.out.println("3. Cập nhật sản phẩm theo mã sản phẩm");
            System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo tên");
            System.out.println("6. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("7. Sắp xếp sản phẩm theo số lượng giảm dần");
            System.out.println("8. Thoát");
            System.out.println("***************************************************");

            System.out.print("Lựa chọn của bạn: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số từ 1-8!");
                continue;
            }

            switch (choice) {

                case 1:
                    productBusiness.addProduct(scanner);
                    break;

                case 2:
                    productBusiness.displayProducts();
                    break;

                case 3:
                    productBusiness.updateProduct(scanner);
                    break;

                case 4:
                    productBusiness.deleteProduct(scanner);
                    break;

                case 5:
                    productBusiness.searchByName(scanner);
                    break;

                case 6:
                    productBusiness.sortByPriceAsc();
                    break;

                case 7:
                    productBusiness.sortByQuantityDesc();
                    break;

                case 8:
                    System.out.println("Đã thoát chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Vui lòng chọn từ 1-8!");
            }
        }
    }
}