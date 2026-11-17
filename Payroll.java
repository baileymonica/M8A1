import java.io.*;

public class Payroll {

    public static void main(String[] args) {

        String inputFile = "pay.csv";
        String outputFile = "output.csv";

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(inputFile));

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(outputFile));

            // Skip input header
            String line = reader.readLine();

            // Output header
            writer.write("EmpID,PayRate,HoursWorked,WeeklyPay");
            writer.newLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int empID = Integer.parseInt(data[0]);
                double payRate = Double.parseDouble(data[1]);
                double hoursWorked = Double.parseDouble(data[2]);

                double weeklyPay;

                if (hoursWorked <= 40) {
                    weeklyPay = payRate * hoursWorked;
                } else {
                    weeklyPay = (40 * payRate)
                            + ((hoursWorked - 40) * payRate * 1.5);
                }

                String outputLine = empID + ","
                        + payRate + ","
                        + hoursWorked + ","
                        + String.format("$%.2f", weeklyPay);

                writer.write(outputLine);
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("output.csv created successfully.");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}