import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

// [ПУНКТ 1: Классы] - Связанный класс №4 (Главный класс)
// [ПУНКТ 9: Интерфейс] - Сначала консоль (Scanner), затем графическое окно (JFrame)
public class MiniPaint {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int brushSize = 10;
        Color brushColor = Color.BLACK;

        System.out.println("=================================");
        System.out.println("   Welcome to Mini-Paint!        ");
        System.out.println("=================================");

        //[ПУНКТ 7: Исключения] - Защита от неверного ввода в консоли
        try {
            System.out.print("Enter brush size (1 to 50): ");
            brushSize = scanner.nextInt();
            
            if (brushSize < 1 || brushSize > 50) {
                System.out.println("Out of range. Default size 10 is set.");
                brushSize = 10;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Not a number! Default size 10 is set.");
            scanner.next(); // Очистка буфера сканера
        }

        // Обновили консольное меню для соответствия палитре
        System.out.println("\nSelect starting brush color:");
        System.out.println("1 - Black   4 - Green   7 - Brown");
        System.out.println("2 - Red     5 - Blue    8 - Pink");
        System.out.println("3 - Yellow  6 - Purple  9 - Orange");
        System.out.print("Your choice: ");

        try {
            int colorChoice = scanner.nextInt();
            switch (colorChoice) {
                case 2 -> brushColor = Color.RED;
                case 3 -> brushColor = Color.YELLOW;
                case 4 -> brushColor = Color.GREEN;
                case 5 -> brushColor = Color.BLUE;
                case 6 -> brushColor = new Color(128, 0, 128); // Фиолетовый
                case 7 -> brushColor = new Color(139, 69, 19); // Коричневый
                case 8 -> brushColor = Color.PINK;
                case 9 -> brushColor = Color.ORANGE;
                default -> brushColor = Color.BLACK;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error. Default black color is set.");
        } finally {
            System.out.println("\nSetup complete. Starting graphic window...");
            System.out.println("Total tools created in this session: " + Tool.toolCount);
        }

        scanner.close();

        // Создаем объект кисти
        Tool myBrush = new Brush(brushColor, brushSize);

        //[ПУНКТ 9: Интерфейс] - Запуск JFrame
        SwingUtilities.invokeLater(() -> startGUI(myBrush));
    }

    //[ПУНКТ 6: Ключевое слово static] - Статический метод для запуска графики
    private static void startGUI(Tool tool) {
        JFrame frame = new JFrame("Mini-Paint (OOP Project)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Наша доска для рисования
        DrawingBoard board = new DrawingBoard(tool);
        frame.add(board, BorderLayout.CENTER);

        // --- ГЛАВНАЯ ПАНЕЛЬ ИНСТРУМЕНТОВ ---
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BorderLayout()); // Разделим на левую (цвета) и правую (кнопки) части

        // 1. Создаем панель палитры
        JPanel palettePanel = new JPanel();
        palettePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        // Массив всех нужных цветов
        Color[] colors = {
            Color.BLACK, 
            Color.RED, 
            Color.YELLOW, 
            Color.GREEN, 
            Color.BLUE, 
            new Color(128, 0, 128), // Фиолетовый (Purple)
            new Color(139, 69, 19), // Коричневый (Brown)
            Color.PINK, 
            Color.ORANGE
        };

        // Проходимся циклом и создаем цветные квадратики (как в Paint)
        for (Color c : colors) {
            JButton colorBtn = new JButton();
            colorBtn.setPreferredSize(new Dimension(30, 30)); // Размер ячейки 30x30
            colorBtn.setBackground(c);                        // Красим фон
            colorBtn.setOpaque(true);                         // Делаем непрозрачным
            colorBtn.setBorderPainted(true);
            colorBtn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); // Серая рамочка
            colorBtn.setFocusPainted(false);                  // Убираем рамку фокуса при клике
            
            // При нажатии меняем цвет кисти
            colorBtn.addActionListener(e -> board.setCurrentTool(new Brush(c, tool.getSize())));
            
            palettePanel.add(colorBtn);
        }

        // 2. Создаем панель кнопок действий (Ластик и Очистить)
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnEraser = new JButton("Eraser");
        btnEraser.addActionListener(e -> board.setCurrentTool(new Eraser(tool.getSize() + 10)));

        JButton btnClear = new JButton("Clear All");
        btnClear.addActionListener(e -> board.clearCanvas());

        actionPanel.add(btnEraser);
        actionPanel.add(btnClear);

        // Добавляем палитру слева, а действия справа на главную панель инструментов
        toolbar.add(palettePanel, BorderLayout.WEST);
        toolbar.add(actionPanel, BorderLayout.EAST);

        // Добавляем панель инструментов вниз окна
        frame.add(toolbar, BorderLayout.SOUTH);
        // ----------------------------------------------------

        frame.setVisible(true);
    }
}