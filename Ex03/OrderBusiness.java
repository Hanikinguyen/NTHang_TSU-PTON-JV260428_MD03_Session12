package ra.business;

import ra.entity.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderBusiness {

    private List<Order> orders = new ArrayList<>();

    // =====================================================
    // 1. THÊM ĐƠN HÀNG
    // =====================================================

    public void addOrder(Scanner scanner) {

        Order order = new Order();

        order.inputData(scanner);

        orders.add(order);

        System.out.println(
                "Thêm đơn hàng thành công!"
        );
    }

    // =====================================================
    // 2. HIỂN THỊ DANH SÁCH
    // Sắp xếp giá trị giảm dần
    // =====================================================

    public void displayOrders() {

        if (orders.isEmpty()) {

            System.out.println(
                    "Danh sách đơn hàng đang trống!"
            );

            return;
        }

        System.out.println(
                "================ DANH SÁCH ĐƠN HÀNG ================"
        );

        orders.stream()
                .sorted(
                        Comparator.comparing(
                                Order::getOrderAmount
                        ).reversed()
                )
                .forEach(System.out::println);
    }

    // =====================================================
    // TÌM ORDER THEO ID
    // =====================================================

    private Optional<Order> findById(int orderId) {

        return orders.stream()
                .filter(order ->
                        order.getOrderId() == orderId
                )
                .findFirst();
    }

    // =====================================================
    // 3. CẬP NHẬT TRẠNG THÁI
    // Pending -> Shipped -> Delivered
    // =====================================================

    public void updateStatus(Scanner scanner) {

        System.out.print(
                "Nhập mã đơn hàng cần cập nhật: "
        );

        int orderId;

        try {

            orderId =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Mã đơn hàng phải là số nguyên!"
            );

            return;
        }

        Optional<Order> optionalOrder =
                findById(orderId);

        if (optionalOrder.isEmpty()) {

            System.out.println(
                    "Không tìm thấy đơn hàng!"
            );

            return;
        }

        Order order = optionalOrder.get();

        System.out.println(
                "Trạng thái hiện tại: "
                        + order.getStatus()
        );

        if (order.getStatus().equals("Pending")) {

            System.out.println(
                    "Bạn có thể chuyển sang Shipped."
            );

            System.out.print(
                    "Nhập trạng thái mới: "
            );

            String newStatus =
                    scanner.nextLine().trim();

            if (newStatus.equalsIgnoreCase("Shipped")) {

                order.setStatus("Shipped");

                System.out.println(
                        "Cập nhật trạng thái thành công!"
                );

            } else {

                System.out.println(
                        "Chỉ được chuyển Pending -> Shipped!"
                );
            }

        } else if (
                order.getStatus().equals("Shipped")
        ) {

            System.out.println(
                    "Bạn có thể chuyển sang Delivered."
            );

            System.out.print(
                    "Nhập trạng thái mới: "
            );

            String newStatus =
                    scanner.nextLine().trim();

            if (newStatus.equalsIgnoreCase("Delivered")) {

                order.setStatus("Delivered");

                System.out.println(
                        "Cập nhật trạng thái thành công!"
                );

            } else {

                System.out.println(
                        "Chỉ được chuyển Shipped -> Delivered!"
                );
            }

        } else {

            System.out.println(
                    "Đơn hàng Delivered không thể cập nhật thêm!"
            );
        }
    }

    // =====================================================
    // 4. XÓA ĐƠN HÀNG
    // Chỉ được xóa khi Pending
    // =====================================================

    public void deleteOrder(Scanner scanner) {

        System.out.print(
                "Nhập mã đơn hàng cần xóa: "
        );

        int orderId;

        try {

            orderId =
                    Integer.parseInt(
                            scanner.nextLine()
                    );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Mã đơn hàng phải là số nguyên!"
            );

            return;
        }

        Optional<Order> optionalOrder =
                findById(orderId);

        if (optionalOrder.isEmpty()) {

            System.out.println(
                    "Không tìm thấy đơn hàng!"
            );

            return;
        }

        Order order = optionalOrder.get();

        // Chỉ được xóa Pending
        if (!order.getStatus().equals("Pending")) {

            System.out.println(
                    "Chỉ được xóa đơn hàng có trạng thái Pending!"
            );

            return;
        }

        System.out.println(
                "Thông tin đơn hàng:"
        );

        System.out.println(order);

        System.out.print(
                "Bạn có chắc chắn muốn xóa? (Y/N): "
        );

        String confirm =
                scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {

            orders.remove(order);

            System.out.println(
                    "Xóa đơn hàng thành công!"
            );

        } else {

            System.out.println(
                    "Đã hủy thao tác xóa."
            );
        }
    }

    // =====================================================
    // 5. TÌM KIẾM THEO TÊN KHÁCH HÀNG
    // Không phân biệt hoa thường
    // =====================================================

    public void searchByCustomerName(
            Scanner scanner) {

        System.out.print(
                "Nhập tên khách hàng cần tìm: "
        );

        String keyword =
                scanner.nextLine()
                        .trim()
                        .toLowerCase();

        List<Order> result =
                orders.stream()
                        .filter(order ->
                                order.getCustomerName()
                                        .toLowerCase()
                                        .contains(keyword)
                        )
                        .collect(Collectors.toList());

        if (result.isEmpty()) {

            System.out.println(
                    "Không tìm thấy đơn hàng!"
            );

        } else {

            System.out.println(
                    "========== KẾT QUẢ TÌM KIẾM =========="
            );

            result.forEach(System.out::println);
        }
    }

    // =====================================================
    // 6. THỐNG KÊ TỔNG SỐ ĐƠN HÀNG
    // =====================================================

    public void countOrders() {

        System.out.println(
                "Tổng số đơn hàng: "
                        + orders.size()
        );
    }

    // =====================================================
    // 7. THỐNG KÊ TỔNG DOANH THU DELIVERED
    // =====================================================

    public void totalDeliveredRevenue() {

        float totalRevenue =
                (float) orders.stream()
                        .filter(order ->
                                order.getStatus()
                                        .equals("Delivered")
                        )
                        .mapToDouble(
                                Order::getOrderAmount
                        )
                        .sum();

        System.out.println(
                "Tổng doanh thu các đơn Delivered: "
                        + totalRevenue
        );
    }

    // =====================================================
    // 8. THỐNG KÊ SỐ ĐƠN THEO TRẠNG THÁI
    // =====================================================

    public void countByStatus() {

        long pending =
                orders.stream()
                        .filter(order ->
                                order.getStatus()
                                        .equals("Pending")
                        )
                        .count();

        long shipped =
                orders.stream()
                        .filter(order ->
                                order.getStatus()
                                        .equals("Shipped")
                        )
                        .count();

        long delivered =
                orders.stream()
                        .filter(order ->
                                order.getStatus()
                                        .equals("Delivered")
                        )
                        .count();

        System.out.println(
                "Pending: " + pending
        );

        System.out.println(
                "Shipped: " + shipped
        );

        System.out.println(
                "Delivered: " + delivered
        );
    }

    // =====================================================
    // 9. TÌM ĐƠN HÀNG CÓ GIÁ TRỊ LỚN NHẤT
    // =====================================================

    public void findMaxOrder() {

        Optional<Order> maxOrder =
                orders.stream()
                        .max(
                                Comparator.comparing(
                                        Order::getOrderAmount
                                )
                        );

        if (maxOrder.isPresent()) {

            System.out.println(
                    "Đơn hàng có giá trị lớn nhất:"
            );

            System.out.println(
                    maxOrder.get()
            );

        } else {

            System.out.println(
                    "Danh sách đơn hàng đang trống!"
            );
        }
    }
}