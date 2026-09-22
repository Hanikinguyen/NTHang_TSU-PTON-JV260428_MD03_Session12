import ra.business.AppointmentBusiness;

import java.util.Scanner;

public class Ex02 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AppointmentBusiness appointmentBusiness =
                new AppointmentBusiness();

        while (true) {

            System.out.println();
            System.out.println(
                    "**************** QUẢN LÝ LỊCH HẸN ****************"
            );

            System.out.println("1. Thêm lịch hẹn");
            System.out.println("2. Hiển thị danh sách lịch hẹn");
            System.out.println(
                    "3. Tìm kiếm lịch hẹn theo tên bệnh nhân"
            );
            System.out.println(
                    "4. Cập nhật lịch hẹn theo mã lịch hẹn"
            );
            System.out.println(
                    "5. Xóa lịch hẹn theo mã lịch hẹn"
            );
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");

            System.out.println(
                    "****************************************************"
            );

            System.out.print(
                    "Lựa chọn của bạn: "
            );

            int choice;

            try {

                choice =
                        Integer.parseInt(
                                scanner.nextLine()
                        );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Vui lòng nhập số từ 1-7!"
                );

                continue;
            }

            switch (choice) {

                case 1:
                    appointmentBusiness
                            .addAppointment(scanner);
                    break;

                case 2:
                    appointmentBusiness
                            .displayAppointments();
                    break;

                case 3:
                    appointmentBusiness
                            .searchByPatientName(scanner);
                    break;

                case 4:
                    appointmentBusiness
                            .updateAppointment(scanner);
                    break;

                case 5:
                    appointmentBusiness
                            .deleteAppointment(scanner);
                    break;

                case 6:
                    appointmentBusiness
                            .statistics();
                    break;

                case 7:
                    System.out.println(
                            "Đã thoát chương trình!"
                    );

                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Vui lòng chọn từ 1-7!"
                    );
            }
        }
    }
}
