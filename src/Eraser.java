import java.awt.Color;
import java.awt.Graphics;

//[ПУНКТ 3: Наследование] - Еще один наследник абстрактного класса
public class Eraser extends Tool {

    public Eraser(int size) {
        // Ластик — это по сути кисть, которая "рисует" белым цветом фона
        super("Eraser", Color.WHITE, size);
    }

    //[ПУНКТ 4: Полиморфизм] - У ластика своя реализация (он рисует квадрат, а не круг)
    @Override
    public void apply(Graphics g, int x, int y) {
        g.setColor(this.getColor());
        
        int currentSize = this.getSize();
        // fillRect - рисует залитый прямоугольник (квадрат)
        g.fillRect(x - currentSize / 2, y - currentSize / 2, currentSize, currentSize);
    }
}