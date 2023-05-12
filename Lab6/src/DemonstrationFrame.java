import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

class DemonstrationFrame extends Frame implements Observer, ActionListener, ItemListener {
    private final List<Figure> figuresList = new ArrayList<>();
    private final Frame frame;
    private final JColorChooser colorChooser;
    private final TextField figureTypeInput;
    private final TextField speedInput;
    private final MyComponentAdapter componentAdapter;
    private final TextField oldIdInput;
    private final TextField newIdInput;
    private final Choice speedModifierChoice;
    private final TextField speedModifierIdInput;
    private final TextField figureIdInput;
    private final Label errorLabel;
    private final Dimension d = new Dimension(250, 20);

    DemonstrationFrame() {
        componentAdapter = new MyComponentAdapter();
        this.addWindowListener(new MyWindowAdapter());
        this.addComponentListener(componentAdapter);

        // Создание УО
        frame = new Frame();
        frame.setSize(new Dimension(450, 1080));
        frame.setTitle("УО");
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.addWindowListener(new MyWindowAdapter());

        // Статус ошибки
        errorLabel = createLabel("");
        errorLabel.setForeground(Color.RED);
        frame.add(errorLabel);

        // Элемент выбора цвета
        colorChooser = new JColorChooser(Color.BLACK);
        colorChooser.setMaximumSize(new Dimension(450, 250));
        frame.add(colorChooser);
        colorChooser.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for (AbstractColorChooserPanel panel : panels) {
            if (!panel.getDisplayName().equals("Swatches")) {
                colorChooser.removeChooserPanel(panel);
            }
        }

        // ЗАПУСК
        // Поле ввода типа фигуры
        frame.add(createLabel("ЗАПУСК"));
        figureTypeInput = new TextField();
        figureTypeInput.setMaximumSize(d);
        frame.add(createLabel("Тип фигуры"));
        frame.add(figureTypeInput);

        // Поля ввода скорости
        speedInput = new TextField();
        speedInput.setMaximumSize(d);
        frame.add(createLabel("Начальная скорость"));
        frame.add(speedInput);

        // Поле ввода номера фигуры
        frame.add(createLabel("Номер фигуры"));
        figureIdInput = new TextField();
        figureIdInput.setMaximumSize(d);
        frame.add(figureIdInput);

        // Кнопка запуска
        Button startButton = new Button("Старт");
        startButton.setMaximumSize(new Dimension(80, 20));
        startButton.setActionCommand("Start");
        startButton.addActionListener(this);
        frame.add(startButton);

        // РЕГУЛИРОВКА СКОРОСТИ
        // Выпададающее меню регулировки скорости
        speedModifierChoice = new Choice();
        for (int i = 1; i <= 5; ++i) {
            speedModifierChoice.add(String.valueOf(i));
        }
        speedModifierChoice.addItemListener(this);
        speedModifierChoice.setMaximumSize(d);

        // Поле ввода номера фигуры
        speedModifierIdInput = new TextField();
        speedModifierIdInput.setMaximumSize(d);

        frame.add(createLabel("РЕГУЛИРОВКА СКОРОСТИ"));
        frame.add(createLabel("Номер объекта"));
        frame.add(speedModifierIdInput);
        frame.add(createLabel("Выбор скорости"));
        frame.add(speedModifierChoice);

        // Кнопка регулировки скорости
        Button updateSpeedButton = new Button("Обновить");
        updateSpeedButton.setMaximumSize(new Dimension(80, 20));
        updateSpeedButton.setActionCommand("Update speed");
        updateSpeedButton.addActionListener(this);
        frame.add(updateSpeedButton);

        // Обновление номера
        // Поля обновления номера
        frame.add(createLabel("ОБНОВЛЕНИЕ НОМЕРА"));
        oldIdInput = new TextField();
        oldIdInput.setMaximumSize(d);
        frame.add(createLabel("Старый номер фигуры"));
        frame.add(oldIdInput);

        newIdInput = new TextField();
        newIdInput.setMaximumSize(d);
        frame.add(createLabel("Новый номер фигуры"));
        frame.add(newIdInput);

        // Кнопка обновления номера
        Button updateIdButton = new Button("Обновить");
        updateIdButton.setMaximumSize(new Dimension(80, 20));
        updateIdButton.setActionCommand("Update id");
        updateIdButton.addActionListener(this);
        frame.add(updateIdButton);

        // Инициализация ДО
        this.setSize(500, 540);
        frame.setVisible(true);
        this.setTitle("ДО");
        this.setVisible(true);
        this.setLocation(450, 0);
    }

    public void update(Observable o, Object arg) {
        if (!figuresList.isEmpty()) {
            for (Figure obj : figuresList) {
                obj.frame = componentAdapter.frame;
            }
        }
        repaint();
    }

    public void paint(Graphics g) {
        if (!figuresList.isEmpty()) {
            for (Figure obj : figuresList) {
                obj.draw(g);
            }
        }
    }

    public void itemStateChanged(ItemEvent iE) {}

    public void actionPerformed(ActionEvent aE) {
        String type = figureTypeInput.getText();
        String speedStr = speedInput.getText();
        String idStr = figureIdInput.getText();
        String oldIdStr = oldIdInput.getText();
        String newIdStr = newIdInput.getText();
        String modifiedIdStr = speedModifierIdInput.getText();

        errorLabel.setText("");
        String str = aE.getActionCommand();
        switch (str) {
            case "Start" ->
                Controllers.addNewFigure(
                    this, frame, errorLabel, colorChooser, figuresList, type, idStr, speedStr);
            case "Update speed" ->
                Controllers.updateSpeed(errorLabel, figuresList, speedModifierChoice, modifiedIdStr);
            case "Update id" ->
                Controllers.updateId(errorLabel, figuresList, oldIdStr, newIdStr);
        }
        repaint();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setAlignment(Label.CENTER);
        label.setMaximumSize(d);

        return  label;
    }
}


