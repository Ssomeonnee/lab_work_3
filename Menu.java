import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


import static java.lang.System.exit;

public class Menu {

    private static int arrays_amount = -1;
    private static ArrayProcess[] array;

    private static SentenceProcess[] sentence;

    private static int sentences_amount = -1;

    private static void instructions(int kind)
    {
        switch (kind){
            case 1:
                System.out.println("Выберете команду для работы:");
                System.out.println("0. Повторный вывод списка команд");
                System.out.println("1. Работа с массивом");
                System.out.println("2. Работа с предложением");
                System.out.println("3. Выход");
                break;
            case 2:
                System.out.println("Выберете действия с массивом:");
                System.out.println("0. Повторный вывод списка команд");
                System.out.println("1. Создать массив");
                System.out.println("2. Преобразовать массив");
                System.out.println("3. Вывести массив");
                System.out.println("4. Удалить нули из массива");
                System.out.println("5. Создать вектор на основе массива");
                System.out.println("6. Заполнить массив новыми значениями");
                System.out.println("7. Получить список индентификаторов созданных массивов");
                System.out.println("8. Записать массив в файл");
                System.out.println("9. Выйти в главное меню");
                break;
            case 3:
                System.out.println("Выберете действия с предложением:");
                System.out.println("0. Повторный вывод списка команд");
                System.out.println("1. Ввести строку");
                System.out.println("2. Найти количество слов между самым коротким и длинным словом");
                System.out.println("3. Получить список индентификаторов созданных предложений");
                System.out.println("4. Записать предложение в файл");
                System.out.println("5. Выйти в главное меню");
                break;
        }

    }

    public static void callMenu()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("**************************");
        System.out.println("Вы в главном меню!");
        System.out.println("**************************");
        instructions(1);
        try {
            int vvod = in.nextInt();

            while (vvod != 3) {
                switch (vvod) {
                    case 0:
                        instructions(1);
                        break;
                    case 1:
                        arrayMenu();
                        break;
                    case 2:
                        sentenceMenu();
                        break;
                    default:
                        System.out.println("Ошибка выбора команды, повторите попытку /:");
                        callMenu();
                        break;
                }
                System.out.println("****Введите следующую команду****");
                System.out.println("(Введите 0 для вывода списка команд)");
                vvod = in.nextInt();
            }
        }
        catch (InputMismatchException e)////////////////////////////
        {
            System.out.println("Неккоректный ввод, повторите попытку /:");
            callMenu();
        }
        System.out.println("Вы вышли из меню...");
        exit(0);

    }

    private static void arrayMenu()
    {

        Scanner in = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("Вы вызвали меню работы с массивом");
        System.out.println("---------------------------------");
        instructions(2);

        try {
            int vvod = in.nextInt();

            while (vvod != 9) {
                if (vvod == 1)
                { arrayCreateMenu();
                    System.out.println("--->>>Выход в меню работы с массивом");}
                else if (vvod == 7) {
                    if (arrays_amount == -1) {
                        System.out.println("Еще не создано ни одного массива!");
                        arrayMenu();
                    }
                    else {
                        System.out.print("индентификаторы массивов: ");
                        for (int i = 0; i < arrays_amount - 1; i++)
                            System.out.print(i + ", ");
                        System.out.println(arrays_amount - 1);
                    }
                }
                else if (vvod == 0)
                    instructions(2);
                else if (vvod<0 || vvod>9) {
                    System.out.println("Ошибка выбора команды, повторите попытку /:");
                    arrayMenu();
                }
                else {
                    System.out.println("Введите индентификатор массива для обработки");
                    int array_name = in.nextInt();
                    if (array_name < 0 || array_name >= arrays_amount) {
                        System.out.println("Такой массив не объявлен!");
                        arrayMenu();
                    }
                    switch (vvod) {
                        case 2:
                            array[array_name].transformArray();
                            break;
                        case 3:
                            array[array_name].outputArray();
                            break;
                        case 4:
                            array[array_name].deleteZeros();
                            break;
                        case 5:
                            array[array_name].createSortedVector();
                            break;
                        case 6:
                            array[array_name].randomise();
                            break;
                        case 8:
                            System.out.println("Введите путь к файлу");
                            in.nextLine();
                            String path = in.nextLine();
                            try {
                                FileInputOutput.arrayOutput(array[array_name].getArrayValues(), path);
                            }
                            catch (WrongPathFormatException e)
                            {
                                System.out.println("Файл для записи массива должен иметь расщирение .bin, повторите попытку /:");
                                arrayMenu();
                            }
                            catch (IOException e) {
                                System.out.println("Ошибка записи в файл, повторите попытку /:");
                                arrayMenu();
                            }
                            break;
                    }
                }
                System.out.println("----Введите следующую команду----");
                System.out.println("(Введите 0 для вывода списка команд)");
                vvod = in.nextInt();
            }
        }
        catch (InputMismatchException e)////////////////////////////
        {
            System.out.println("Неккоректный ввод, повторите попытку /:");
            arrayMenu();
        }
        callMenu();
    }

    private static int arrayCreateMenu()
    {
        Scanner in = new Scanner(System.in);

        try
        {
            if (arrays_amount == -1) {
                System.out.println("Введите количество массивов, с которым вы хотели бы работать во время выполнения программы");
                int vvod_arrays_number = in.nextInt();
                if (vvod_arrays_number > 0) {
                    array = new ArrayProcess[vvod_arrays_number];
                    arrays_amount=0;
                }
                else {
                    System.out.println("Количество массивов должно быть больше 0, повторите попытку /:");
                    arrayCreateMenu();
                    return 0;
                }
            }

            if (arrays_amount>=array.length)
            {
                System.out.println("Вы уже создали максиамальное количество массивов - "+array.length);
                System.out.println("--->>>Выход в меню работы с массивом");
                arrayMenu();
                return 0;
            }

            System.out.println("[][][] Выберете способ задания массива: [][][]");
            System.out.println("1. Cоздание массива, заполненного случайными числами, с произвольным количеством столбцов (>1)");
            System.out.println("2. Создание массива по умолчанию");
            System.out.println("3. Создание клона существующего массива");
            System.out.println("4. Записать массив из файла");


            int fill_in = in.nextInt();
                switch (fill_in) {

                    case 1:

                        System.out.println("Введите количество столбцов массива");
                        int rows = in.nextInt();

                        if (rows <= 1) {
                            System.out.println("Количество столбцов в массиве должно быть больше 1, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                        array[arrays_amount] = new ArrayProcess(rows);
                        break;

                    case 2:
                        array[arrays_amount] = new ArrayProcess();
                        break;

                    case 3:
                        System.out.println("Введите имя массива для клонирования");
                        int clon = in.nextInt();

                        if (clon < 0 || clon > arrays_amount - 1)///??????????
                        {
                            System.out.println("Такой массив не объявлен, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }

                        array[arrays_amount] = new ArrayProcess(array[clon]);
                        break;
                    case 4:
                        System.out.println("Введите путь к файлу");
                        in.nextLine();
                        String path = in.nextLine();

                        try {
                            array[arrays_amount] = new ArrayProcess(FileInputOutput.arrayInput(path));
                            break;
                        }
                        catch (NotAPathException e)
                        {
                            System.out.println("Некорректный ввод пути, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                        catch (WrongPathFormatException e)
                        {
                            System.out.println("Файл для чтения массива должен иметь расширение .bin, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                        catch (WrongDataFormatException e)
                        {
                            System.out.println("Некорректная запись массива - число элементов массива меньше указанного в начале количества элементов, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                       catch (ArrayIndexOutOfBoundsException e)
                        {
                            System.out.println("Некорректная запись массива - число элементов массива больше указанного в начале количества элементов, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                        catch (FileNotFoundException e)
                        {
                            System.out.println("Файл не найден, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                        catch (IOException e) {
                            System.out.println("Формат данных файла не соответствует требуему формату записи массива, повторите попытку /:");
                            arrayCreateMenu();
                            return 0;
                        }
                    default:
                        System.out.println("Ошибка выбора команды, повторите попытку /:");
                        arrayCreateMenu();
                        break;
                }
            System.out.println("[] Массиву присвоен индентификатор: " + arrays_amount + " []");
            arrays_amount++;
            return 1;
        }
        catch (NullPointerException e)////////////////////////////
        {return 0;}
        catch (InputMismatchException e)////////////////////////////
        {
            System.out.println("Некоректный ввод, повторите попытку /:");
            arrayCreateMenu();
            return 0;
        }
    }

    private static void sentenceMenu()
    {
        Scanner in = new Scanner(System.in);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("Вы вызвали меню работы с предложением");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        instructions(3);

        try {
            int vvod = in.nextInt();

            while (vvod != 5) {
                switch (vvod) {
                    case 0:
                        instructions(3);
                        break;
                    case 1:
                        sentenceCreateMenu();
                        System.out.println("--->>>Выход в меню работы с предложением");
                        break;
                    case 2:

                        System.out.println("Введите индентификатор предложения для обработки");
                        int sentence_name = in.nextInt();
                        if (sentence_name < 0 || sentence_name >= sentences_amount) {///????????
                            System.out.println("Такое предложение не создано!");
                            sentenceMenu();
                            break;
                        }
                        sentence[sentence_name].findingAmountOfWords();
                        break;
                    case 3:
                        if (sentences_amount == -1)
                            System.out.println("Еще не создано ни одного предложения!");
                        else {
                            System.out.print("Имена предложений: ");
                            for (int i = 0; i < sentences_amount - 1; i++)
                                System.out.print(i + ", ");
                            System.out.println(sentences_amount - 1);
                        }
                        break;
                    case 4:
                        System.out.println("Введите индентификатор предложения для обработки");
                        sentence_name = in.nextInt();
                        if (sentence_name < 0 || sentence_name >= sentences_amount) {///????????
                            System.out.println("Такое предложение не создано!");
                            sentenceMenu();
                            break;
                        }
                        System.out.println("Введите путь к файлу");
                        in.nextLine();
                        String path = in.nextLine();
                        try {
                            FileInputOutput.textOutput(sentence[sentence_name].getSentence(), path);
                        }
                        catch (WrongPathFormatException e)
                        {
                            System.out.println("Файл для записи предложения должен иметь расширение .txt, повторите попытку /:");
                            sentenceMenu();
                        }
                        catch (IOException e) {
                            System.out.println("Ошибка записи в файл, повторите попытку /:");
                            sentenceMenu();
                        }
                        break;
                    default:
                        System.out.println("Ошибка выбора команды, повторите попытку /:");
                        sentenceMenu();
                        break;
                }
                System.out.println(">>>Введите следующую команду>>>>");
                System.out.println("(Введите 0 для вывода списка команд)");
                vvod = in.nextInt();
            }
        }
        catch (InputMismatchException e)////////////////////////////
        {
            System.out.println("Неккоректный ввод, повторите попытку /:");
            arrayMenu();
        }
        callMenu();
    }

    private static int sentenceCreateMenu()
    {
        Scanner in = new Scanner(System.in);
        try {
            if (sentences_amount == -1) {
                sentences_amount = 0;
                System.out.println("Введите количество предложений, с которым вы хотели бы работать во время выполнения программы");
                int vvod_sentences_number = in.nextInt();
                if (vvod_sentences_number > 0)
                    sentence = new SentenceProcess[vvod_sentences_number];
                else {
                    System.out.println("Некорректный ввод, повторите попытку /:");
                    sentenceCreateMenu();
                    return 0;
                }
            }
            System.out.println("..... Выберете способ задания предложения: .....");
            System.out.println("1. Ввод предложения");
            System.out.println("2. Создание предложения по умолчанию");
            System.out.println("3. Записать предложение из файла");

            int vvod = in.nextInt();

            if (sentences_amount >= sentence.length) {
                System.out.println("Вы уже создали максиамальное количество предложений - " + sentence.length);
                System.out.println("--->>>Выход в меню работы с предложением");
                sentenceMenu();
                return 0;
            }

            switch (vvod) {
                case 1:
                    in.nextLine();
                    System.out.println("Введите предложение");
                    String str = in.nextLine();
                    str = str.replaceAll("[^a-zA-Zа-яА-я ]", "");
                    String[] word = str.split(" ");
                    if ((word.length == 0)) {
                        System.out.println("Неккоректный ввод - предложение пустое, либо введены только небуквенные символы, повторите попытку /:");
                        {
                            sentenceCreateMenu();
                            return 0;
                        }
                    } else if (word.length == 1) {
                        System.out.println("В предложении всего одно слово, повторите попытку /:");
                        {
                            System.out.println("Файл для чтения предложения должен быть текстовым, повторите попытку /:");
                            sentenceCreateMenu();
                            return 0;
                        }
                    } else if ((word.length == 2) && (word[0].isEmpty())) {
                        System.out.println("В предложении всего одно слово, повторите попытку /:");
                        {
                            sentenceCreateMenu();
                            return 0;
                        }
                    }
                    sentence[sentences_amount] = new SentenceProcess(str);
                    break;
                case 2:
                    sentence[sentences_amount] = new SentenceProcess();
                    break;
                case 3:
                    System.out.println("Введите путь к файлу");
                    in.nextLine();
                    String path = in.nextLine();
                    try {
                        sentence[sentences_amount] = new SentenceProcess(FileInputOutput.textInput(path));

                        System.out.println("[] Предложению присвоен индентификатор: " + sentences_amount + " []");

                        sentences_amount++;
                    }
                    catch (NotAPathException e)
                    {
                        System.out.println("Некорректный ввод пути, повторите попытку /:");
                        sentenceCreateMenu();
                        return 0;
                    }
                    catch (WrongPathFormatException e)
                    {
                        System.out.println("Файл для чтения предложения должен иметь расширение .txt, повторите попытку /:");
                        sentenceCreateMenu();
                        return 0;
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println("Файл не найден, повторите попытку /:");
                        sentenceCreateMenu();
                        return 0;
                    }
                    catch (IOException e) {
                        System.out.println("Формат данных файла не соответствует требуему формату записи предложения, повторите попытку /:");
                        sentenceCreateMenu();
                    }
                    break;
                default:
                    System.out.println("Ошибка выбора команды, повторите попытку /:");
                    sentenceCreateMenu();
                    break;
            }
            System.out.println("[] Предложению присвоен индентификатор: " + sentences_amount + " []");
            sentences_amount++;
            return 1;
        }
        catch (NullPointerException e)////////////////////////////
        {return 0;}
        catch (InputMismatchException e)////////////////////////////
        {
            System.out.println("Неккоректный ввод, повторите попытку /:");
            sentenceCreateMenu();
            return 0;
        }
    }













}

