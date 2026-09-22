package ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Appointment {

    private String appointmentId;
    private String patientName;
    private String phoneNumber;
    private LocalDate appointmentDate;
    private String doctor;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Constructor không tham số
    public Appointment() {
    }

    // Constructor đầy đủ tham số
    public Appointment(String appointmentId, String patientName,
                       String phoneNumber, LocalDate appointmentDate,
                       String doctor) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
    }

    // Getter và Setter
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {

        // Appointment ID
        while (true) {
            System.out.print("Nhập mã lịch hẹn (6 ký tự): ");
            String id = scanner.nextLine().trim();

            if (id.length() != 6) {
                System.out.println("Mã lịch hẹn phải gồm đúng 6 ký tự!");
            } else {
                appointmentId = id;
                break;
            }
        }

        // Patient name
        while (true) {
            System.out.print("Nhập tên bệnh nhân: ");
            String name = scanner.nextLine().trim();

            if (name.length() < 10 || name.length() > 50) {
                System.out.println(
                        "Tên bệnh nhân phải từ 10 đến 50 ký tự!"
                );
            } else {
                patientName = name;
                break;
            }
        }

        // Phone
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String phone = scanner.nextLine().trim();

            // Số điện thoại Việt Nam:
            // 0 + 9 chữ số
            // hoặc +84 + 9 chữ số
            String phoneRegex = "^(0[3|5|7|8|9][0-9]{8}|\\+84[3|5|7|8|9][0-9]{8})$";

            if (!phone.matches(phoneRegex)) {
                System.out.println(
                        "Số điện thoại không đúng định dạng!"
                );
            } else {
                phoneNumber = phone;
                break;
            }
        }

        // Appointment date
        while (true) {
            System.out.print("Nhập ngày hẹn (dd/MM/yyyy): ");
            String date = scanner.nextLine().trim();

            try {
                appointmentDate = LocalDate.parse(date, FORMATTER);
                break;
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Ngày không hợp lệ! Vui lòng nhập theo dd/MM/yyyy."
                );
            }
        }

        // Doctor
        while (true) {
            System.out.print("Nhập bác sĩ phụ trách: ");
            String doctorName = scanner.nextLine().trim();

            if (doctorName.length() > 200) {
                System.out.println(
                        "Tên bác sĩ không được quá 200 ký tự!"
                );
            } else {
                doctor = doctorName;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", appointmentDate=" +
                appointmentDate.format(FORMATTER) +
                ", doctor='" + doctor + '\'' +
                '}';
    }
}