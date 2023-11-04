import static java.lang.System.exit;

public class SentenceProcess {

    private String[] word;

    public SentenceProcess(String sentence) {
        sentence = sentence.replaceAll("[^a-zA-Zа-яА-я ]", "");
        word = sentence.split(" ");
        /*System.out.println();
        if ((word.length == 0)) {
            System.out.println("Неккоректный ввод - предложение пустое, либо введены только небуквенные символы");
            exit(0);
        } else if (word.length == 1) {
            System.out.println("В предложении всего одно слово");
            exit(0);
        } else if ((word.length == 2) && (word[0].isEmpty())) {
            System.out.println("В предложении всего одно слово");
            exit(0);
        }*/
        System.out.println("Объект класса SentenceProcess успешно создан!");
    }
    public SentenceProcess(){
        System.out.println();
        System.out.println("Объект класса SentenceProcess успешно создан!");
        String sentence = "Здесь должно быть предложение."; // по умолчанию
        sentence = sentence.replaceAll("[^a-zA-Zа-яА-я ]", "");
        word = sentence.split(" ");
    }
    public SentenceProcess(String[] fake_array)
    {
        word=new String[fake_array.length];
        for (int i=0; i<fake_array.length;i++)
            word[i]=fake_array[i];
    }

    public String[] getSentence()
    {
        String[] str = new String[word.length];
        for (int i=0; i<word.length; i++)
            str[i]=word[i];
        return str;
    }

    public int findingAmountOfWords() {

        int max = 0;
        int min = 1000;

        for (String i : word) // поиск длины самого короткого и длинного слова
        {
            if (i.length() > max)
                max = i.length();
            if ((i.length() < min) && (!i.isEmpty()))
                min = i.length();
        }
        if (max == min)
        {
            System.out.println("В предложении все слова равны");
            return 0;
        }

        System.out.println();
        System.out.println("Максимальная длина слова " + max);

        System.out.println("Минимальная длина слова " + min);

        int[] max_id = new int[word.length];// массив для хранения индексов максимумов
        int[] min_id = new int[word.length];// массив для хранения индексов минимумов

        int max_c = 0;
        int min_c = 0;

        for (int i = 0; i < word.length; i++) // заполнение массивов индеками свмых коротких и длинных слов
        {
            if (word[i].length() == max) {
                max_id[max_c] = i;
                max_c++;
            }
            if (word[i].length() == min) {
                min_id[min_c] = i;
                min_c++;
            }
        }
        System.out.println();
        System.out.println("Индексы самых длинных слов");
        for (int i = 0; i < max_c; i++) // вывод массива индексов самых длинных слов
        {
            System.out.print(word[max_id[i]] + " - " + max_id[i] + " ");
        }
        System.out.println();
        System.out.println("Индексы самых коротких слов");
        for (int i = 0; i < min_c; i++) // вывод массива индексов самых коротких слов
        {
            System.out.print(word[min_id[i]] + " - " + min_id[i] + " ");
        }

        System.out.println();
        System.out.println("Вывод количества слов между самым длинным и коротким:");
        int var = 1;
        for (int i = 0; i < max_c; i++) {
            for (int j = 0; j < min_c; j++) {
                System.out.println("Вариант " + var + " : ");
                System.out.println("Элемент " + max_id[i] + " - " + word[max_id[i]] + " и " + "элемент " + min_id[j] + " - " + word[min_id[j]]);
                if ((max_id[i] - min_id[j]) > 0)
                    System.out.println("Количество слов между данными словами: " + (max_id[i] - min_id[j] - 1) + " ");
                else
                    System.out.println("Количество слов между данными словами: " + (Math.abs(max_id[i] - min_id[j]) - 1) + " ");
                var++;
            }
        }
        return 1;
    }

}

