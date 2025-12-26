package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int sum = 0;
            for (String oneData: data) {
                String[] parts = oneData.split(" ");
                String employeeName = parts[1];
                if (employeeName.equals(name)) {
                    String employeeStringDate = parts[0];
                    LocalDate employeeDate = LocalDate.parse(employeeStringDate, formatter);
                    if (!employeeDate.isAfter(to) && !employeeDate.isBefore(from)) {
                        int hours = Integer.parseInt(parts[2]);
                        int salary = Integer.parseInt(parts[3]);
                        sum += hours * salary;
                    }
                }

            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }

        return builder.toString();
    }
}
