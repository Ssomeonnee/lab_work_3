import java.io.*;
public class FileInputOutput {

    public static void arrayOutput(double[][] fake_array, String filename) throws IOException, WrongPathFormatException {

        if (!filename.contains(".bin"))
            throw new WrongPathFormatException("Файл для записи массива должен быть бинарным!");

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
            // записываем значения
            dos.writeDouble(fake_array.length);
            dos.writeDouble(fake_array[0].length);
            for (int i = 0; i < fake_array.length; i++) {
                for (int j = 0; j < fake_array[i].length; j++) {
                    dos.writeDouble(fake_array[i][j]);
                }
            }
            System.out.println("Данные успешно записаны!!!");
    }

    public static double[][] arrayInput(String filename) throws FileNotFoundException, IOException, WrongDataFormatException, WrongPathFormatException, ArrayIndexOutOfBoundsException, NotAPathException {

        if (!filename.contains("\\"))
            throw new NotAPathException("Это не путь!");

        if (!filename.contains(".bin"))
            throw new WrongPathFormatException("Файл для чтения массива должен быть бинарным!");

        DataInputStream dos = new DataInputStream(new FileInputStream(filename));
            // записываем значения


                int len_rows = (int) dos.readDouble();
                int len_cols = (int) dos.readDouble();

                double[][] fake_array = new double[len_rows][len_cols];

                int c = 0;
                for (int i = 0; i < fake_array.length; i++) {
                    for (int j = 0; j < fake_array[0].length; j++) {
                        fake_array[i][j] = dos.readDouble();
                        c++;
                    }
                }

                if (c != len_rows * len_cols)
                    throw new WrongDataFormatException("Некорректная запись массива - число элементов массива меньше указанного в начале количества элементов!");

                System.out.println("Данные успешно прочитаны!!!");
                return fake_array;
    }
    public static void textOutput(String[] fake_sentence, String filename) throws IOException, WrongPathFormatException {

        if (!filename.contains(".txt"))
            throw new WrongPathFormatException("Файл для записи предложения должен быть текстовым!");

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename));
            // записываем значения
            dos.writeUTF(String.valueOf(fake_sentence.length));
            //dos.writeDouble(fake_array[0].length);
            for (int i = 0; i < fake_sentence.length; i++) {
                dos.writeUTF(fake_sentence[i]);
            }
            System.out.println("Данные успешно записаны!!!");
    }

    public static String[] textInput(String filename) throws FileNotFoundException, IOException, WrongPathFormatException, NotAPathException {

        if (!filename.contains("\\"))
            throw new NotAPathException("Это не путь!");

        if (!filename.contains(".txt"))
            throw new WrongPathFormatException("Файл для чтения предложения должен быть текстовым!");

        DataInputStream dos = new DataInputStream(new FileInputStream(filename));
            // записываем значения

            int len = Integer.parseInt(dos.readUTF());
            //int len_cols = (int) dos.readDouble();

            String[] fake_sentence = new String[len];

            for (int i = 0; i < fake_sentence.length; i++) {
                fake_sentence[i]=dos.readUTF();
            }
            System.out.println("Данные успешно прочитаны!!!");
            return fake_sentence;

    }
}
