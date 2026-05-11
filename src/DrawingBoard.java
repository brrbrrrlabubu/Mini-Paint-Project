import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

//[ПУНКТ 1: Классы] - Связанный класс №3
//[ПУНКТ 8: Интерфейсы] - Имплементация MouseMotionListener для отслеживания мыши
public class DrawingBoard extends JPanel implements MouseMotionListener {
    
    private Tool currentTool;
    private BufferedImage canvas; 
    private Graphics2D g2d;

    public DrawingBoard(Tool tool) {
        this.currentTool = tool;
        this.setBackground(Color.WHITE);
        this.addMouseMotionListener(this);
    }

    // НОВЫЙ МЕТОД: Позволяет менять инструмент (кисть на ластик или менять цвет)
    public void setCurrentTool(Tool tool) {
        this.currentTool = tool;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (canvas == null) {
            canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            g2d = canvas.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clearCanvas();
        }
        g.drawImage(canvas, 0, 0, null);
    }

    // Сделали метод public, чтобы кнопка "Очистить" могла его вызывать
    public void clearCanvas() {
        g2d.setPaint(Color.WHITE);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentTool != null && g2d != null) {
            currentTool.apply(g2d, e.getX(), e.getY());
            repaint(); 
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}