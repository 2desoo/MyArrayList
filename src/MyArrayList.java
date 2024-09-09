import java.util.Arrays;

public class MyArrayList<E extends Comparable<E>> {  // Добавили ограничение на Comparable
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

    // Метод быстрой сортировки (quicksort)
    public void quicksort() {
        quicksort(0, size - 1);
    }

    // Вспомогательный метод быстрой сортировки с указанием индексов
    private void quicksort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);  // Определяем точку опоры (пивот)
            quicksort(low, pivotIndex - 1);  // Рекурсивный вызов для левой части
            quicksort(pivotIndex + 1, high);  // Рекурсивный вызов для правой части
        }
    }

    // Метод разделения массива на части относительно опорного элемента
    private int partition(int low, int high) {
        E pivot = (E) elements[high];  // Опорный элемент
        int i = low - 1;  // Индекс меньшего элемента

        for (int j = low; j < high; j++) {
            if (((E) elements[j]).compareTo(pivot) <= 0) {  // Сравнение с пивотом
                i++;
                swap(i, j);  // Меняем местами
            }
        }

        swap(i + 1, high);  // Меняем местами опорный элемент
        return i + 1;  // Возвращаем индекс опорного элемента
    }

    // Метод для обмена элементов в массиве
    private void swap(int i, int j) {
        E temp = (E) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
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
        myList.add(3);
        myList.add(1);
        myList.add(4);
        myList.add(1);
        myList.add(5);
        myList.add(9);
        myList.add(2);
        myList.add(6);
        myList.add(5);
        System.out.println("Before sorting: " + myList); // Вывод: Before sorting: [3, 1, 4, 1, 5, 9, 2, 6, 5]

        myList.quicksort();
        System.out.println("After sorting: " + myList); // Вывод: After sorting: [1, 1, 2, 3, 4, 5, 5, 6, 9]
    }
}


