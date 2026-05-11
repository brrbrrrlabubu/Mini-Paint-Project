
import java.awt.Color;
import java.awt.Graphics;

// [ПУНКТ 1: Классы] - Связанный класс №2
//[ПУНКТ 3: Наследование] - Класс Brush наследует абстрактный класс Tool
public class Brush extends Tool {

    public Brush(Color color, int size) {
        //[ПУНКТ 6: Ключевое слово super] - Вызов конструктора родительского класса Tool
        super("Кисть", color, size);
    }

    // [ПУНКТ 4: Полиморфизм] - Переопределение абстрактного метода
    @Override
    public void apply(Graphics g, int x, int y) {
        // [ПУНКТ 6: Ключевое слово this] - Использование геттеров текущего объекта
        g.setColor(this.getColor());
        
        // Рисуем круг (кисть). Смещаем координаты на половину размера, чтобы курсор был в центре
        int currentSize = this.getSize();
        g.fillOval(x - currentSize / 2, y - currentSize / 2, currentSize, currentSize);
    }
}
