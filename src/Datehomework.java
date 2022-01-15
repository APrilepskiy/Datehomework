import java.io.*;
import java.util.Date;

public class Datehomework  {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Программа считает количество дней прошедших с начала года и записывает это число в файл.");
        System.out.println("Введите имя файла указав его расширение. Например, \"example.txt\": ");
        String name = reader.readLine();
        creatFile(name);
    }

    public static void creatFile(String fileName) {
        File file = new File(fileName);
        boolean isExists = true;

        {
            try {
                isExists = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Невозможно создать файл.");
            }
        }

        if (isExists) {
            System.out.println("Файл создан в текущем каталоге.");
            int numberDays = daysFrom();
            System.out.println("С начала года прошло " + numberDays + " дней.");

            try(FileWriter writer = new FileWriter(file, false))
            {
                // запись строки в файл
                String text = Integer.toString(numberDays);
                writer.write(text);
                writer.flush();
                System.out.println("Информация записана в файл.");
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }

        }
        else {
            System.out.println("Файл с таким именем уже существует.");
        }
    }

    public static int daysFrom() {
        Date yearStartTime = new Date();
        yearStartTime.setHours(0);
        yearStartTime.setMinutes(0);
        yearStartTime.setSeconds(0);

        yearStartTime.setDate(1);      // первое число
        yearStartTime.setMonth(0);     // месяц январь, нумерация для месяцев 0-11

        Date currentTime = new Date();
        long msTimeDistance = currentTime.getTime() - yearStartTime.getTime();
        long msDay = 24 * 60 * 60 * 1000;  //сколько миллисекунд в одних сутках

        int dayCount = (int) (msTimeDistance/msDay); //количество целых дней

        return dayCount;
    }

}
