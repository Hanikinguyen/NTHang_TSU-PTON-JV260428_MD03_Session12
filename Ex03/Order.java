package ra.entity;

import java.util.Scanner;

public class Order {

    private static int nextId = 1;

    private int orderId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private float orderAmount;
    private String status;

    // Constructor không tham số
    public Order() {
        this.orderId = nextId++;
        this.status = "Pending";
    }

    // Constructor đầy đủ tham số
    public Order(int orderId, String customerName,
                 String phoneNumber, String address,
                 float orderAmount, String status) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderAmount = orderAmount;
        this.status = status;

        if (orderId >= nextId) {
            nextId = orderId + 1;
        }
    }

    // Getter và Setter

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {

        // Tên khách hàng
        while (true) {

            System.out.print("Nhập tên khách hàng: ");

            String name = scanner.nextLine().trim();

            if (name.length() < 6 || name.length() > 100) {

                System.out.println(
                        "Tên khách hàng phải từ 6-100 ký tự!"
                );

            } else {

                customerName = name;
                break;
            }
        }

        // Số điện thoại
        while (true) {

            System.out.print("Nhập số điện thoại: ");

            String phone = scanner.nextLine().trim();

            // Số điện thoại di động Việt Nam
            String phoneRegex =
                    "^(0[35789][0-9]{8}|\\+84[35789][0-9]{8})$";

            if (!phone.matches(phoneRegex)) {

                System.out.println(
                        "Số điện thoại không đúng định dạng!"
                );

            } else {

                phoneNumber = phone;
                break;
            }
        }

        // Địa chỉ
        while (true) {

            System.out.print("Nhập địa chỉ giao hàng: ");

            String addressInput =
                    scanner.nextLine().trim();

            if (addressInput.isEmpty()) {

                System.out.println(
                        "Địa chỉ không được để trống!"
                );

            } else {

                address = addressInput;
                break;
            }
        }

        // Giá trị đơn hàng
        while (true) {

            try {

                System.out.print(
                        "Nhập giá trị đơn hàng: "
                );

                float amount =
                        Float.parseFloat(
                                scanner.nextLine()
                        );

                if (amount <= 0) {

                    System.out.println(
                            "Giá trị đơn hàng phải > 0!"
                    );

                } else {

                    orderAmount = amount;
                    break;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Giá trị đơn hàng phải là số!"
                );
            }
        }

        // Khi thêm mới luôn mặc định Pending
        status = "Pending";
    }

    @Override
    public String toString() {

        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", orderAmount=" + orderAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
