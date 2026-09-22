package ra.business;

import ra.entity.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentBusiness {

    private List<Appointment> appointments = new ArrayList<>();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // =====================================================
    // 1. THÊM LỊCH HẸN
    // =====================================================

    public void addAppointment(Scanner scanner) {

        Appointment appointment = new Appointment();

        while (true) {

            appointment.inputData(scanner);

            // Kiểm tra mã không trùng
            boolean exists = appointments.stream()
                    .anyMatch(a ->
                            a.getAppointmentId()
                                    .equalsIgnoreCase(
                                            appointment.getAppointmentId()
                                    )
                    );

            if (exists) {
                System.out.println(
                        "Mã lịch hẹn đã tồn tại! Vui lòng nhập lại."
                );
            } else {
                appointments.add(appointment);
                System.out.println(
                        "Thêm lịch hẹn thành công!"
                );
                break;
            }
        }
    }

    // =====================================================
    // 2. HIỂN THỊ DANH SÁCH
    // =====================================================

    public void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("Danh sách lịch hẹn đang trống!");
            return;
        }

        System.out.println(
                "================ DANH SÁCH LỊCH HẸN ================"
        );

        appointments.stream()
                .sorted(
                        Comparator.comparing(
                                Appointment::getAppointmentDate
                        )
                )
                .forEach(System.out::println);
    }

    // =====================================================
    // 3. TÌM KIẾM THEO TÊN BỆNH NHÂN
    // =====================================================

    public void searchByPatientName(Scanner scanner) {

        System.out.print("Nhập tên bệnh nhân cần tìm: ");
        String keyword = scanner.nextLine()
                .trim()
                .toLowerCase();

        List<Appointment> result = appointments.stream()
                .filter(a ->
                        a.getPatientName()
                                .toLowerCase()
                                .contains(keyword)
                )
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println(
                    "Không tìm thấy lịch hẹn!"
            );
        } else {
            System.out.println(
                    "========== KẾT QUẢ TÌM KIẾM =========="
            );

            result.forEach(System.out::println);
        }
    }

    // =====================================================
    // 4. CẬP NHẬT LỊCH HẸN
    // =====================================================

    public void updateAppointment(Scanner scanner) {

        System.out.print(
                "Nhập mã lịch hẹn cần cập nhật: "
        );

        String id = scanner.nextLine().trim();

        Optional<Appointment> optionalAppointment =
                appointments.stream()
                        .filter(a ->
                                a.getAppointmentId()
                                        .equalsIgnoreCase(id)
                        )
                        .findFirst();

        optionalAppointment.ifPresentOrElse(
                appointment -> updateData(scanner, appointment),

                () -> System.out.println(
                        "Không tìm thấy lịch hẹn có mã: " + id
                )
        );
    }

    // Nhập dữ liệu cập nhật
    private void updateData(
            Scanner scanner,
            Appointment appointment) {

        System.out.println(
                "========== CẬP NHẬT LỊCH HẸN =========="
        );

        // Cập nhật tên
        while (true) {

            System.out.print("Nhập tên bệnh nhân mới: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println(
                        "Tên bệnh nhân phải từ 10-50 ký tự!"
                );
            } else {
                appointment.setPatientName(name);
                break;
            }
        }

        // Cập nhật số điện thoại
        while (true) {

            System.out.print("Nhập số điện thoại mới: ");
            String phone = scanner.nextLine().trim();

            String phoneRegex =
                    "^(0[3|5|7|8|9][0-9]{8}|\\+84[3|5|7|8|9][0-9]{8})$";

            if (!phone.matches(phoneRegex)) {
                System.out.println(
                        "Số điện thoại không đúng định dạng!"
                );
            } else {
                appointment.setPhoneNumber(phone);
                break;
            }
        }

        // Cập nhật ngày
        while (true) {

            System.out.print(
                    "Nhập ngày hẹn mới (dd/MM/yyyy): "
            );

            String date = scanner.nextLine().trim();

            try {

                LocalDate appointmentDate =
                        LocalDate.parse(date, FORMATTER);

                appointment.setAppointmentDate(
                        appointmentDate
                );

                break;

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Ngày không hợp lệ!"
                );
            }
        }

        // Cập nhật bác sĩ
        while (true) {

            System.out.print(
                    "Nhập bác sĩ phụ trách mới: "
            );

            String doctor = scanner.nextLine().trim();

            if (doctor.length() > 200) {

                System.out.println(
                        "Tên bác sĩ không được quá 200 ký tự!"
                );

            } else {

                appointment.setDoctor(doctor);
                break;
            }
        }

        System.out.println(
                "Cập nhật lịch hẹn thành công!"
        );
    }

    // =====================================================
    // 5. XÓA LỊCH HẸN
    // =====================================================

    public void deleteAppointment(Scanner scanner) {

        System.out.print(
                "Nhập mã lịch hẹn cần xóa: "
        );

        String id = scanner.nextLine().trim();

        Optional<Appointment> optionalAppointment =
                appointments.stream()
                        .filter(a ->
                                a.getAppointmentId()
                                        .equalsIgnoreCase(id)
                        )
                        .findFirst();

        if (optionalAppointment.isEmpty()) {

            System.out.println(
                    "Không tìm thấy lịch hẹn!"
            );

            return;
        }

        Appointment appointment =
                optionalAppointment.get();

        System.out.println(
                "Thông tin lịch hẹn:"
        );

        System.out.println(appointment);

        System.out.print(
                "Bạn có chắc chắn muốn xóa? (Y/N): "
        );

        String confirm =
                scanner.nextLine().trim();

        if (confirm.equalsIgnoreCase("Y")) {

            appointments.remove(appointment);

            System.out.println(
                    "Xóa lịch hẹn thành công!"
            );

        } else {

            System.out.println(
                    "Đã hủy thao tác xóa."
            );
        }
    }

    // =====================================================
    // 6. THỐNG KÊ
    // =====================================================

    public void statistics() {

        System.out.println(
                "================ THỐNG KÊ ================"
        );

        // Tổng số lịch hẹn
        System.out.println(
                "Tổng số lịch hẹn: "
                        + appointments.size()
        );

        // Số lịch hẹn theo bác sĩ
        Map<String, Long> statistics =
                appointments.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Appointment::getDoctor,
                                        Collectors.counting()
                                )
                        );

        System.out.println(
                "Số lịch hẹn theo từng bác sĩ:"
        );

        if (statistics.isEmpty()) {

            System.out.println(
                    "Chưa có lịch hẹn."
            );

        } else {

            statistics.forEach(
                    (doctor, count) ->
                            System.out.println(
                                    doctor + ": "
                                            + count
                                            + " lịch hẹn"
                            )
            );
        }
    }
}
