import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Main {
    JFrame main_frame;

    JLabel description;
    JLabel description1;
    JLabel description2;

    JLabel apex_A_label;
    JLabel apex_B_label;
    JLabel apex_C_label;

    JLabel result_label;

    private JTextField apex_A_x_tf;
    private JTextField apex_B_x_tf;
    private JTextField apex_C_x_tf;
    private JTextField apex_A_y_tf;
    private JTextField apex_B_y_tf;
    private JTextField apex_C_y_tf;

    private JButton calc_button;

    private void errorDisplay(String error){
        result_label.setText("<html>Ошибка! " + error + "</html>");
    }

    private Double calcSide(Double x1, Double x2, Double y1, Double y2){
        return Math.abs(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }

    private void calcPerimetrNDisplay(){
        String temp;
        Double A_x, A_y, B_x, B_y, C_x, C_y;
        try {
            temp = apex_A_x_tf.getText();
            A_x = Double.parseDouble(temp);
        }
        catch (Exception e){
            errorDisplay("Не удалось считать поля координат!<br>Проверьте правильность ввода!");
            apex_A_x_tf.setText("");
            apex_A_x_tf.requestFocus();
            return;
        }
        try {
            temp = apex_A_y_tf.getText();
            A_y = Double.parseDouble(temp);
        }
        catch (Exception e){
            errorDisplay("Не удалось считать поля координат!<br>Проверьте правильность ввода!");
            apex_A_y_tf.setText("");
            apex_A_y_tf.requestFocus();
            return;
        }
        try {
            temp = apex_B_x_tf.getText();
            B_x = Double.parseDouble(temp);
        }
        catch (Exception e){
            errorDisplay("Не удалось считать поля координат!<br>Проверьте правильность ввода!");
            apex_B_x_tf.setText("");
            apex_B_x_tf.requestFocus();
            return;
        }
        try {
            temp = apex_B_y_tf.getText();
            B_y = Double.parseDouble(temp);
        }
        catch (Exception e){
            errorDisplay("Не удалось считать поля координат!<br>Проверьте правильность ввода!");
            apex_B_y_tf.setText("");
            apex_B_y_tf.requestFocus();
            return;
        }
        try {
            temp = apex_C_x_tf.getText();
            C_x = Double.parseDouble(temp);
        }
        catch (Exception e){
            errorDisplay("Не удалось считать поля координат!<br>Проверьте правильность ввода!");
            apex_C_x_tf.setText("");
            apex_C_x_tf.requestFocus();
            return;
        }
        try {
            temp = apex_C_y_tf.getText();
            C_y = Double.parseDouble(temp);
        }
        catch (Exception e){
            errorDisplay("Не удалось считать поля координат!<br>Проверьте правильность ввода!");
            apex_C_y_tf.setText("");
            apex_C_y_tf.requestFocus();
            return;
        }
        Double AB = calcSide(A_x, B_x, A_y, B_y);
        Double BC = calcSide(B_x, C_x, B_y, C_y);
        Double CA = calcSide(C_x, A_x, C_y, A_y);
        if (AB + BC <= CA || AB + CA <= BC || BC + CA <= AB){
            errorDisplay("Треугольник не существует!");
        }
        else {
            String result = String.format("%.3f", AB + BC + CA);
            result_label.setText("Периметр треугольника: " + result);
        }
    }

    private JLabel initLabel(int x, int y, int w, int h, String text){
        JLabel label = new JLabel();
        label.setBounds(x, y, w, h);
        label.setText(text);
        return label;
    }

    private JTextField initTextField(int x, int y, int w, int h, String text){
        JTextField text_field = new JTextField();
        text_field.setBounds(x, y, w, h);
        text_field.setText(text);
        text_field.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {}
            @Override
            public void focusLost(FocusEvent e) {
                calcPerimetrNDisplay();
            }
        });
        return text_field;
    }

    Main(){
        main_frame = new JFrame();
        main_frame.setBounds(10, 20, 610, 310);
        main_frame.setTitle("Лабораторная работа № 1");
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setLayout(null);

        description = initLabel(15, 15, 450, 60,
                "<html>Программа производит расчет периметра треугольника.<br>" +
               "Для расчета требуется заполнить поля координат для каждой вершины</html>");
        main_frame.add(description);

        description1 = initLabel(220, 75, 200, 30, "Координаты х");
        main_frame.add(description1);

        description2 = initLabel(405, 75, 200, 30, "Координаты y");
        main_frame.add(description2);

        apex_A_label = initLabel(15, 105, 200, 30, "Введите координаты вершины А");
        main_frame.add(apex_A_label);

        apex_A_x_tf = initTextField(220, 105, 175, 30, "");
        main_frame.add(apex_A_x_tf);

        apex_A_y_tf = initTextField(405, 105, 175, 30, "");
        main_frame.add(apex_A_y_tf);

        apex_B_label = initLabel(15, 140, 200, 30, "Введите координаты вершины B");
        main_frame.add(apex_B_label);

        apex_B_x_tf = initTextField(220, 140, 175, 30, "");
        main_frame.add(apex_B_x_tf);

        apex_B_y_tf = initTextField(405, 140, 175, 30, "");
        main_frame.add(apex_B_y_tf);

        apex_C_label = initLabel(15, 175, 200, 30, "Введите координаты вершины C");
        main_frame.add(apex_C_label);

        apex_C_x_tf = initTextField(220, 175, 175, 30, "");
        main_frame.add(apex_C_x_tf);

        apex_C_y_tf = initTextField(405, 175, 175, 30, "");
        main_frame.add(apex_C_y_tf);

        result_label = initLabel(220, 210, 300, 60, "");
        main_frame.add(result_label);

        calc_button = new JButton();
        calc_button.setBounds(15, 225, 200, 30);
        calc_button.setFont(new Font(result_label.getFont().getFontName(), Font.PLAIN, 10));
        calc_button.setText("Произвести расчет периметра");
        main_frame.add(calc_button);

        main_frame.show();
    }
    public static void main(String[] args){
        Main main_object = new Main();
    }
}
