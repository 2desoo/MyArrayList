import java.util.Arrays;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10; // Начальная ёмкость массива
    private Object[] elements; // Массив элементов
    private int size = 0; // Текущее количество элементов в списке

    // Конструктор по умолчанию
    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    // Конструктор с заданной начальной ёмкостью
    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        elements = new Object[initialCapacity];
    }

    // Метод добавления элемента в конец списка
    public void add(E element) {
        ensureCapacity(); // Проверка и увеличение ёмкости массива при необходимости
        elements[size++] = element; // Добавление элемента и увеличение size
    }

    // Метод вставки элемента по индексу
    public void add(int index, E element) {
        rangeCheckForAdd(index); // Проверка допустимости индекса
        ensureCapacity(); // Проверка и увеличение ёмкости массива при необходимости
        System.arraycopy(elements, index, elements, index + 1, size - index); // Сдвиг элементов вправо
        elements[index] = element; // Вставка элемента
        size++;
    }

    // Метод получения элемента по индексу
    public E get(int index) {
        rangeCheck(index); // Проверка допустимости индекса
        return (E) elements[index]; // Возврат элемента
    }

    // Метод удаления элемента по индексу
    public E remove(int index) {
        rangeCheck(index); // Проверка допустимости индекса
        E removedElement = (E) elements[index]; // Сохранение удаляемого элемента
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved); // Сдвиг элементов влево
        }
        elements[--size] = null; // Очистка последнего элемента и уменьшение size
        return removedElement; // Возврат удаленного элемента
    }

    // Метод проверки ёмкости массива и увеличение её при необходимости
    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2; // Увеличение ёмкости в 2 раза
            elements = Arrays.copyOf(elements, newCapacity); // Копирование элементов в новый массив
        }
    }

    // Метод получения текущего размера списка
    public int size() {
        return size;
    }

    // Метод проверки выхода индекса за пределы допустимого диапазона
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Метод проверки выхода индекса за пределы допустимого диапазона для вставки
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // Метод преобразования списка в строку
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Пример использования
    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        System.out.println(myList); // Вывод: [1, 2, 3]

        myList.add(1, 5); // Вставка элемента 5 на позицию 1
        System.out.println(myList); // Вывод: [1, 5, 2, 3]

        myList.remove(2); // Удаление элемента по индексу 2
        System.out.println(myList); // Вывод: [1, 5, 3]

        System.out.println(myList.get(1)); // Вывод: 5
        System.out.println("Size: " + myList.size()); // Вывод: Size: 3
    }
}

