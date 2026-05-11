
import java.awt.Color;
import java.awt.Graphics;

// [ПУНКТ 1: Классы] - Связанный класс №1
// [ПУНКТ 5: Абстракция] - Использование абстрактного класса
public abstract class Tool {
    
    // [ПУНКТ 2: Инкапсуляция] - Все поля private
    private String name;
    private Color color;
    private int size;
    
    //[ПУНКТ 6: Ключевое слово static] - Статическое поле для подсчета созданных инструментов
    public static int toolCount = 0;

    public Tool(String name, Color color, int size) {
        // [ПУНКТ 6: Ключевое слово this] - Обращение к полям текущего объекта
        this.name = name;
        this.color = color;
        this.size = size;
        toolCount++;
    }

    // [ПУНКТ 2: Инкапсуляция] - public геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    //[ПУНКТ 5: Абстракция] - Абстрактный метод без реализации
    // Реализация будет зависеть от конкретного инструмента (кисть, ластик и т.д.)
    public abstract void apply(Graphics g, int x, int y);
}