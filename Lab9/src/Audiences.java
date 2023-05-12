import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Audiences extends JPanel implements ActionListener {
    private static JFrame mainFrame = null;
    private static Connection connection = null;
    private static ResultSet result = null;
    private static Statement statement = null;
    private static String SQL = null;
    private final JTextField textFieldFind;
    private final DefaultTableModel tableShowModel;
    private final DefaultTableModel tableYoungModel;
    private final JTable tableShow;
    private final JTable tableYoung;
    private final JLabel labelFindCol;
    private final JScrollPane paneYoung;

    public Audiences() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        //Создание панели "Управление".
        JPanel panelControl = new JPanel();
        int width_window = 1280;
        panelControl.setPreferredSize(new Dimension(width_window, 60));
        panelControl.setBorder(BorderFactory.createTitledBorder("Управление"));
        add(Box.createRigidArea(new Dimension(0, 10))); // Отступ 10 пикселей
        panelControl.setLayout(new FlowLayout());

        JButton buttonShow = new JButton("Просмотреть");
        buttonShow.addActionListener(this);
        JButton buttonCreate = new JButton("Создать");
        buttonCreate.addActionListener(this);
        JButton buttonEdit = new JButton("Редактировать");
        buttonEdit.addActionListener(this);
        JButton buttonDelete = new JButton("Удалить");
        buttonDelete.addActionListener(this);
        JButton buttonYoung = new JButton("Молодые");
        buttonYoung.addActionListener(this);
        JButton buttonSum = new JButton("Сумма площадей");
        buttonSum.addActionListener(this);
        JButton buttonOrderBuilding = new JButton("Упорядочить по 1 столбцу");
        buttonOrderBuilding.addActionListener(this);
        JButton buttonOrderNumber = new JButton("Упорядочить по 2 столбцу");
        buttonOrderNumber.addActionListener(this);
        JButton buttonReset = new JButton("Cброс");
        buttonReset.addActionListener(this);

        panelControl.add(buttonShow);
        panelControl.add(buttonCreate);
        panelControl.add(buttonEdit);
        panelControl.add(buttonDelete);
        panelControl.add(buttonYoung);
        panelControl.add(buttonSum);
        panelControl.add(buttonOrderBuilding);
        panelControl.add(buttonOrderNumber);
        panelControl.add(buttonReset);
        add(panelControl);

        //Создание панели "Поиск".
        JPanel panelFind = new JPanel();
        panelFind.setPreferredSize(new Dimension(width_window, 50));
        panelFind.setBorder(BorderFactory.createTitledBorder("Поиск"));
        panelFind.setLayout(new GridLayout());
        textFieldFind = new JTextField();
        JButton buttonFind = new JButton("Поиск");
        buttonFind.addActionListener(this);
        panelFind.add(textFieldFind);
        panelFind.add(buttonFind);
        add(Box.createRigidArea(new Dimension(0, 10))); // Отступ сверху вниз на 10 пикселей
        add(panelFind);

        //Создание панели "Список аудиторий".
        JPanel audienceShow = new JPanel();
        audienceShow.setPreferredSize(new Dimension(width_window, 200));
        audienceShow.setLayout(new BoxLayout(audienceShow, BoxLayout.Y_AXIS));
        audienceShow.setBorder(BorderFactory.createTitledBorder("Список аудиторий"));
        add(Box.createRigidArea(new Dimension(0, 10))); // Отступ сверху вниз на 10 пикселей

        tableShowModel = new DefaultTableModel(new Object[]
                {"Здание", "Номер", "Имя", "Площадь", "ФИО", "Должность", "Номер телефона", "Возраст"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableShow = new JTable();
        tableShow.setModel(tableShowModel);

        JScrollPane paneShow = new JScrollPane(tableShow);
        audienceShow.add(paneShow);
        labelFindCol = new JLabel("Найдено записей: 0");
        audienceShow.add(labelFindCol);
        add(audienceShow);

        //Создание панели "Молодые ответственные".
        JPanel youngShow = new JPanel();
        youngShow.setPreferredSize(new Dimension(width_window, 150));
        youngShow.setLayout(new BoxLayout(youngShow, BoxLayout.Y_AXIS));
        youngShow.setBorder(BorderFactory.createTitledBorder("Молодые ответственные"));
        add(Box.createRigidArea(new Dimension(0, 10))); // Отступ сверху вниз на 10 пикселей

        tableYoungModel = new DefaultTableModel(new Object[]
                {"ФИО", "Должность", "Номер телефона", "Возраст"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableYoung = new JTable();
        tableYoung.setModel(tableYoungModel);

        paneYoung = new JScrollPane(tableYoung);
        youngShow.add(paneYoung);
        add(youngShow);

        // DB connection
        try {
            String dbURL = "jdbc:postgresql://localhost:5432/Audiences";
            String user_name = "postgres";
            String user_password = "admin";
            connection = DriverManager.getConnection(dbURL, user_name, user_password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Учебные аудитории");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame = frame;
        JComponent componentPanelAudiences = new Audiences();
        frame.setContentPane(componentPanelAudiences);
        frame.setPreferredSize(new Dimension(1280, 600));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Audiences::createAndShowGUI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int dataToSize = 11;
        String[] dataTo = new String[dataToSize];

        for (int i = 0; i < dataToSize; i++) {
            dataTo[i] = "";
        }

        int delta_size_dialog = 20;
        if ("Создать".equals(command)) {
            JDialog dialogContact = new JDialog(mainFrame,
                    "Новая аудитория", JDialog.DEFAULT_MODALITY_TYPE);

            PanelContact panelContact = new PanelContact(command, dataTo);
            dialogContact.setBounds(
                    delta_size_dialog, delta_size_dialog,
                    panelContact.getContactPanelWidth() + 3 * delta_size_dialog,
                    panelContact.getContactPanelHeight() + delta_size_dialog);
            dialogContact.add(panelContact);
            dialogContact.setVisible(true);
        }

        // Редактировать или просмотреть
        try {
            if ((command.equals("Редактировать") || command.equals("Просмотреть"))
                    && result != null && tableShow.getSelectedRow() > -1) {
                result.first();
                do {
                    String value = tableShowModel.getValueAt(tableShow.getSelectedRow(), 4).toString();
                    if (result.getString("fio").equals(value)) {
                        dataTo[0] = result.getString("id");
                        dataTo[1] = result.getString("building");
                        dataTo[2] = result.getString("number");
                        dataTo[3] = result.getString("name");
                        dataTo[4] = result.getString("square");
                        dataTo[5] = result.getString("ic_id");
                        dataTo[6] = result.getString("audience_id");
                        dataTo[7] = result.getString("fio");
                        dataTo[8] = result.getString("position");
                        dataTo[9] = result.getString("phone_number");
                        dataTo[10] = result.getString("age");

                        String title = "";
                        if (command.equals("Редактировать")) {
                            title = "Изменить аудиторию";
                        }
                        if (command.equals("Просмотреть")) {
                            title = "Просмотреть аудиторию";
                        }

                        JDialog dialogContact = new JDialog(
                                mainFrame,
                                title,
                                JDialog.DEFAULT_MODALITY_TYPE);
                        PanelContact panelContact = new PanelContact(command, dataTo);
                        dialogContact.setBounds(
                                delta_size_dialog,
                                delta_size_dialog,
                                panelContact.getContactPanelWidth() + 3 * delta_size_dialog,
                                panelContact.getContactPanelHeight() + delta_size_dialog);
                        dialogContact.add(panelContact);
                        dialogContact.setVisible(true);
                        break;
                    }
                } while (result.next());
            }
        } catch (SQLException err1) {
            System.out.println(err1.getMessage());
        }

        if (command.equals("Поиск")) {
            findByString(textFieldFind.getText(), 0);
        }

        if (command.equals("Упорядочить по 1 столбцу")) {
            findByString("", 1);
        }

        if (command.equals("Упорядочить по 2 столбцу")) {
            findByString("", 2);
        }

        if (command.equals("Молодые")) {
            findYoungest();
        }

        if (command.equals("Сумма площадей")) {
            countSquare();
        }

        try {
            if (command.equals("Удалить") && result != null && tableShow.getSelectedRow() > -1) {
                result.first();
                do {
                    String value = tableShowModel.getValueAt(tableShow.getSelectedRow(), 4).toString();
                    if (result.getString("fio").equals(value)) {
                        String deleteIC = "DELETE FROM IC WHERE ic_id = " + result.getString("ic_id");
                        String deleteAudience = "DELETE FROM Audience WHERE id = " + result.getString("id");

                        PreparedStatement IC;
                        PreparedStatement audience;
                        connection.setAutoCommit(true);

                        IC = connection.prepareStatement(deleteIC);
                        audience = connection.prepareStatement(deleteAudience);
                        IC.executeUpdate();
                        audience.executeUpdate();
                        IC.close();
                        audience.close();
                        findByString("", 0);

                        break;
                    }
                } while (result.next());
            }
        } catch (SQLException err1) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
                JOptionPane.showMessageDialog(
                        this, "Транзакция на удаление не выполнена.\nСмотрите сообщения в консоли.");
                System.out.println(err1.getMessage());
            } catch (SQLException err2) {
                System.out.println(err2.getMessage());
            }
        }
    }

    private void countSquare() {
        if ((tableShow.getSelectedRow() == -1))
            return;
        String fio = tableShowModel.getValueAt(tableShow.getSelectedRow(), 4).toString();
        try {
            SQL = "select sum(square) from (select square from audience join ic on Audience.id = ic.audience_id " +
                    "where fio = " + "'" + fio + "'" + ") AS sub";
            result = statement.executeQuery(SQL);
            result.next();
            String value = result.getString("sum");
            JOptionPane.showMessageDialog(this, value);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private void findByString(String textFind, int column) {
        try {
            while (tableShowModel.getRowCount() > 0) {
                tableShowModel.removeRow(0);
            }

            String orderBy = "";

            if (column == 1)
                orderBy = "ORDER BY building";
            else if (column == 2)
                orderBy = "ORDER BY number";

            SQL =   "SELECT Audience.*, IC.* FROM Audience JOIN IC ON Audience.id = IC.audience_id " +
                    "WHERE Audience.name ILIKE '" + textFind + "%' " + orderBy;
            result = statement.executeQuery(SQL);
            while (result.next()) {
                String building = result.getString("building");
                String number = result.getString("number");
                String name = result.getString("name");
                String square = result.getString("square");
                String fio = result.getString("fio");
                String position = result.getString("position");
                String phoneNumber = result.getString("phone_number");
                String age = result.getString("age");
                tableShowModel.addRow(new Object[]
                        {building, number, name, square, fio, position, phoneNumber, age});
            }
            labelFindCol.setText("Найдено записей: " + tableShowModel.getRowCount());
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
    private void findYoungest() {
        try {
            while (tableYoungModel.getRowCount() > 0) {
                tableYoungModel.removeRow(0);
            }

            SQL = "select fio, position, phone_number, age from IC where age = (SELECT min(age) FROM IC) order by fio";
            result = statement.executeQuery(SQL);
            while (result.next()) {
                String fio = result.getString("fio");
                String position = result.getString("position");
                String phoneNumber = result.getString("phone_number");
                String age = result.getString("age");
                tableYoungModel.addRow(new Object[]
                        {fio, position, phoneNumber, age});
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    class PanelContact extends JPanel implements ActionListener {
        private final int width_window = 300;
        private final int height_window = 300;
        private final String mode;
        private final String[] dataTo;

        private final JTextField txtFieldBuilding;
        private final JTextField txtFieldNumber;
        private final JTextField txtFieldName;
        private final JTextField txtFieldSquare;
        private final JTextField txtFieldFIO;
        private final JTextField txtFieldPosition;
        private final JTextField txtFieldPhoneNumber;
        private final JTextField txtFieldAge;

        public PanelContact(String mode, String[] dataTo) {
            super();
            this.mode = mode;
            this.dataTo = dataTo;

            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            setPreferredSize(new Dimension(width_window, height_window));
            JPanel panelUp = new JPanel(); //Панель для размещения панелей
            JPanel panelLabel = new JPanel();
            JPanel panelText = new JPanel();
            JPanel panelButton = new JPanel();

            // Labels
            JLabel labelBuilding = new JLabel("Здание");
            JLabel labelNumber = new JLabel("Номер");
            JLabel labelName = new JLabel("Имя");
            JLabel labelSquare = new JLabel("Площадь");
            JLabel labelFIO = new JLabel("ФИО");
            JLabel labelPosition = new JLabel("Должность");
            JLabel labelPhoneNumber = new JLabel("Номер тел.");
            JLabel labelAge = new JLabel("Возраст");

            // Fields
            txtFieldBuilding = new JTextField(dataTo[1]);
            txtFieldNumber = new JTextField(dataTo[2]);
            txtFieldName = new JTextField(dataTo[3]);
            txtFieldSquare = new JTextField(dataTo[4]);
            txtFieldFIO = new JTextField(dataTo[7]);
            txtFieldPosition = new JTextField(dataTo[8]);
            txtFieldPhoneNumber = new JTextField(dataTo[9]);
            txtFieldAge = new JTextField(dataTo[10]);

            JButton buttonApply = new JButton("Принять");
            buttonApply.addActionListener(this);
            JButton buttonCancel = new JButton("Отменить");
            buttonCancel.addActionListener(this);

            int height_button_panel = 40;
            int height_gap = 3;
            panelUp.setPreferredSize(new Dimension(width_window,
                    height_window - height_button_panel - height_gap));
            panelUp.setBorder(BorderFactory.createBevelBorder(1));
            add(panelUp);
            panelUp.setLayout(new BoxLayout(panelUp, BoxLayout.LINE_AXIS));
            panelLabel.setPreferredSize(new Dimension(
                    width_window / 3,
                    height_window - height_button_panel - height_gap));
            panelLabel.setBorder(BorderFactory.createBevelBorder(1));
            panelLabel.setLayout(new GridLayout(8, 1));

            panelLabel.add(labelBuilding);
            panelLabel.add(labelNumber);
            panelLabel.add(labelName);
            panelLabel.add(labelSquare);
            panelLabel.add(labelFIO);
            panelLabel.add(labelPosition);
            panelLabel.add(labelPhoneNumber);
            panelLabel.add(labelAge);

            panelText.setPreferredSize(new Dimension(
                    2 * width_window / 3,
                    height_window - height_button_panel - height_gap));
            panelText.setBorder(BorderFactory.createBevelBorder(1));
            panelText.setLayout(new GridLayout(8, 1));


            // Setup
            panelText.add(txtFieldBuilding);
            panelText.add(txtFieldNumber);
            panelText.add(txtFieldName);
            panelText.add(txtFieldSquare);
            panelText.add(txtFieldFIO);
            panelText.add(txtFieldPosition);
            panelText.add(txtFieldPhoneNumber);
            panelText.add(txtFieldAge);

            panelUp.add(panelLabel);
            panelUp.add(panelText);
            add(Box.createRigidArea(new Dimension(0, height_gap)));
            panelButton.setPreferredSize(new Dimension(width_window, height_button_panel));
            panelButton.setBorder(BorderFactory.createBevelBorder(1));
            add(panelButton);
            panelButton.setLayout(new FlowLayout());
            panelButton.add(buttonApply);
            panelButton.add(buttonCancel);

            if ("Просмотреть".equals(mode)) {
                buttonApply.setEnabled(false);
                txtFieldBuilding.setEditable(false);
                txtFieldNumber.setEditable(false);
                txtFieldName.setEditable(false);
                txtFieldSquare.setEditable(false);
                txtFieldFIO.setEditable(false);
                txtFieldPosition.setEditable(false);
                txtFieldPhoneNumber.setEditable(false);
                txtFieldAge.setEditable(false);
            }
        }

        public int getContactPanelWidth() {
            return width_window;
        }

        public int getContactPanelHeight() {
            return height_window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(this);
            if (command.equals("Отменить")) {
                dialog.dispose();
            }
            if (command.equals("Принять")) {
                String building = txtFieldBuilding.getText();
                String number = txtFieldNumber.getText();
                String name = txtFieldName.getText();
                String square = txtFieldSquare.getText();
                String fio = txtFieldFIO.getText();
                String position = txtFieldPosition.getText();
                String phoneNumber = txtFieldPhoneNumber.getText();
                String age = txtFieldAge.getText();

                if (isNumber(building) && isNumber(number) && isWord(name) && isNumber(square)
                        && isFIO(fio) && isWord(position) && isTelephone(phoneNumber)
                        && isNumber(age)) {
                    String updateAudience = null;
                    String updateIC = null;
                    int audience_id;

                    // max ID
                    try {
                        result = statement.executeQuery("SELECT id FROM Audience ORDER BY id DESC limit 1");
                        result.next();
                        audience_id = Integer.parseInt(result.getString("id")) + 1;
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                        return;
                    }

                    if (mode.equals("Создать")) {
                        updateAudience =
                                String.format("INSERT INTO Audience (id, building, number, name, square) " +
                                        "OVERRIDING SYSTEM VALUE VALUES (%s, %s, %s, '%s', %s)",
                                        audience_id, building, number, name, square);
                        updateIC =
                                String.format("INSERT INTO IC (audience_id, fio, position, phone_number, age) " +
                                                "VALUES (%s, '%s', '%s', '%s', %s)",
                                        audience_id, fio, position, phoneNumber, age);
                    }

                    if (mode.equals("Редактировать")) {
                        updateAudience =
                                String.format("UPDATE Audience " +
                                                "SET building = %s, number = %s, name = '%s', square = %s " +
                                                "WHERE id = %s",
                                        building, number, name, square, dataTo[0]);
                        updateIC =
                                String.format("UPDATE IC SET " +
                                                "fio = '%s', position = '%s', phone_number = '%s', age = %s " +
                                                "WHERE ic_id = %s",
                                        fio, position, phoneNumber, age, dataTo[5]);
                    }

                    // DB
                    try {
                        PreparedStatement audience;
                        PreparedStatement IC;
                        connection.setAutoCommit(true);

                        audience = connection.prepareStatement(updateAudience);
                        int res = audience.executeUpdate();
//                        System.out.println(res);

                        IC = connection.prepareStatement(updateIC);
                        res = IC.executeUpdate();
//                        System.out.println(res);

                        audience.close();
                        IC.close();

                    } catch (SQLException err1) {
                        try {
                            connection.setAutoCommit(false);
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Транзакция на создание/изменение не выполнена.\nСмотрите сообщения в консоли.");
                            System.out.println(err1.getMessage());
                            connection.rollback();
                            connection.setAutoCommit(true);
                        } catch (SQLException err2) {
                            System.out.println(err2.getMessage());
                        }
                    }
                    findByString("", 0);
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Исправьте введённые данные");
                }
            }
        }

        private boolean isNumber(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e){
                return false;
            }
        }

        private Boolean isTelephone(String s) {
            if (s.length() != 11) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i)))
                    return false;
            }
            return true;
        }

        private Boolean isFIO(String s) {
            String[] fio = s.split(" ");
            if (fio.length < 2 || fio.length > 3)
                return false;
            for (String word: fio)
                if (!isWord(word))
                    return false;
            return true;
        }

        private boolean isWord(String word) {
            for (int i = 0; i < word.length(); i++) {
                if (!Character.isAlphabetic(word.charAt(i)))
                    return false;
            }
            return true;
        }
    }
}