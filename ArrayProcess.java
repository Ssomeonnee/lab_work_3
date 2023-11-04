import static java.lang.System.exit;

public class ArrayProcess {
    private int size;
    private double[][] array;
    private int amount;

    public ArrayProcess(int size){
        this.size=size;
        array = new double[2][size];
        randomise();
        System.out.println();
        System.out.println("Объект класса ArrayProcess успешно создан!");
    }
    public ArrayProcess(){ //значения по умолчанию
        size=2;
        array = new double[2][size];
        System.out.println("Заполнение массива...");
        array[0][0] = 1.5;
        array[0][1] = 0;
        array[1][0] = 0;
        array[1][1] = 27.2;
        System.out.println("Массив успешно заполнен!");
        System.out.println();
        System.out.println("Объект класса ArrayProcess успешно создан!");
    }

    public ArrayProcess(double[][] fake_array) {
        size=fake_array[0].length;
        array=new double[2][fake_array[0].length];
        for (int i=0; i<fake_array.length; i++)
            for (int j=0; j<fake_array[i].length; j++)
                array[i][j]=fake_array[i][j];
    }

    public ArrayProcess(ArrayProcess original){ // конструктор клонирования
        size=original.size;
        amount=original.amount;
        array=new double[2][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = original.array[i][j];
            }
        }
        System.out.println();
        System.out.println("Объект класса ArrayProcess успешно клонирован!");
    }
    public double[][] getArrayValues() {
        double[][] fake_array = new double[2][size];
        for (int i=0; i<fake_array.length; i++) {
            for (int j = 0; j < fake_array[i].length; j++) {
                fake_array[i][j]=array[i][j];
            }
        }
        return fake_array;
    }
    public void randomise() {
        System.out.println("Заполнение массива...");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                array[i][j] = (int) (Math.random() * 200) - 100;
        }
        System.out.println("Массив успешно заполнен!");
        amount=-1;
    }
    public void outputArray() // вывод массива
    {
        System.out.println();
        System.out.println("Вывод массива");
        int main_amount = 0;
        if (amount > 0)
            main_amount = 2 * size;

        for (double[] i : array) {
            for (double j : i) {
                if (amount <= 0)
                    System.out.print(j + " ");
                else {
                    if (main_amount != amount) {
                        System.out.print(j + " ");
                        main_amount--;
                    } else
                        System.out.print(" ");
                }
            }
            System.out.println();

        }
    }
    public void transformArray() // преобразование массива
    {
        double sum = 0;
        for (double[] i : array) {
            for (double j : i)
                sum += j;
        }
        System.out.println();
        System.out.println("Преобразование массива...");

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
                array[i][j] = array[i][j] * sum;
        }
        System.out.println("Массив успешно преобразован!");
    }
    private int countZeros(){
        amount=0;
        for (double[] i : array) {
            for (double j : i) {
                if (j == 0.0)
                    amount++; // количество 0 в массиве
            }
        }
        return amount;
    }
    private void removeCurrentZero() {
        int c = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0)
                    c = 1;
                if ((c == 1) && (j == array[i].length - 1) && (!((i == array.length-1) && (j == array[i].length - 1))))
                    array[i][j] = array[i + 1][j - 1];
                else if ((c == 1) && (!((i == array.length-1) && (j == array[i].length - 1))))
                    array[i][j] = array[i][j + 1];
            }
        }
    }
    public int deleteZeros() {

        amount = countZeros();
        if (amount == size * 2) { // если количество нулей равно количеству элементов массива
            System.out.println();
            System.out.println("Массив - полностью нулевой. Все элементы массива удалены");
            return 0;
        }
        else {
            if (amount == 0){ // если в массиве нет нулей
                System.out.println();
                System.out.println("Массив не содержит нулевые элементы");
                return 0;
            }
            else {
                System.out.println();
                System.out.println("Удаление нулей...");
                int zero_number = amount;
                while (zero_number != 0) {
                    removeCurrentZero();
                    zero_number--;
                }
                System.out.println("Нули успешно удалены!");
            }
        }
        return 1;
    }
    public int createSortedVector() {

        System.out.println();
        System.out.println("Создание отсортированного по возрастанию одномерного массива...");
        //int main_amount = size * 2;
        double[] vector = new double[size * 2];
        int c = 0;
        for (double[] i : array) { // заполнение вектора
            for (double j : i) {
                vector[c] = j;
                c++;
            }
        }
        for (int i = 0; i < vector.length; i++) { // сортировка вектора
            for (int j = 0; j < vector.length - 1; j++) {
                if (vector[j] > vector[j + 1]) {
                    double temp = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = temp;
                }
            }
        }
        System.out.println("Одномерный массив успешно создан!");
        System.out.println();
        System.out.println("Вывод отсортированного одномерного массива");
        for (double i : vector) {
            System.out.print(i + " ");
        }
        System.out.println();
        return 1;
    }


}

