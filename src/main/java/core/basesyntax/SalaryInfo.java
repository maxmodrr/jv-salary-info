package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String baseMessage = "Report for period " + dateFrom + " - " + dateTo;
        String[] results = new String[names.length];
        int dateFromInteger = convertDateToInteger(dateFrom);
        int dateToInteger = convertDateToInteger(dateTo);

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int sum = 0;
            for (String oneData: data) {
                String[] parts = oneData.split(" ");
                if (parts[1].equals(name)) {
                    int employeeDate = convertDateToInteger(parts[0]);
                    if (employeeDate >= dateFromInteger && employeeDate <= dateToInteger) {
                        int hours = Integer.parseInt(parts[2]);
                        int salary = Integer.parseInt(parts[3]);
                        sum += hours * salary;
                    }
                }

            }
            results[i] = name + " - " + sum;
        }
        StringBuilder builder = new StringBuilder();

        for (String result: results) {
            builder.append("\n").append(result);
        }

        return baseMessage + builder.toString();
    }

    private int convertDateToInteger(String data) {
        String[] parts = data.split("\\.");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        return year * 10000 + month * 100 + day;
    }
}
