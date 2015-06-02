package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main extends Application {
    private TextField userTextField;

    private static boolean matchDigits(String s) {
        Pattern p = Pattern.compile("^[0-9]");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    private static boolean matchNoDigits(String s) {
        Pattern p = Pattern.compile("\\W");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);

        /******** Сетка ************/

        BorderPane rootNode = new BorderPane();
        GridPane displayGrid = new GridPane();
        GridPane buttonsGrid = new GridPane();
        buttonsGrid.setVgap(10);
        buttonsGrid.setHgap(10);
        rootNode.setTop(displayGrid);
        rootNode.setCenter(buttonsGrid);

        displayGrid.setPadding(new Insets(15, 15, 0, 15));
        buttonsGrid.setPadding(new Insets(15, 15, 15, 15));
        Scene scene = new Scene(rootNode, 260, 285);
        primaryStage.setScene(scene);

        userTextField = new TextField();
        userTextField.setPrefSize(230, 50);

        displayGrid.add(userTextField, 0, 0);

//        userTextField.setOnKeyPressed(keyEvent -> {
//            if (keyEvent.getCode() == KeyCode.ENTER) {
//                userTextField.setText(Double.toString(new Calc().calculate(userTextField.getText())));
//            } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
//                userTextField.setText("");
//            }
//        });
        /********* кнопки*******/

        Button btn = new Button("(");
        Button btn1 = new Button("7");
        Button btn2 = new Button("4");
        Button btn3 = new Button("1");
        Button btn4 = new Button("0");
        Button btn5 = new Button(")");
        Button btn6 = new Button("8");
        Button btn7 = new Button("5");
        Button btn8 = new Button("2");
        Button btn9 = new Button(".");
        Button btn10 = new Button("^");
        Button btn11 = new Button("9");
        Button btn12 = new Button("6");
        Button btn13 = new Button("3");
        Button btn14 = new Button("C");
        Button btn15 = new Button("/");
        Button btn16 = new Button("*");
        Button btn17 = new Button("+");
        Button btn18 = new Button("-");
        Button btn19 = new Button("=");

        buttonsGrid.add(btn, 0, 0);
        buttonsGrid.add(btn1, 0, 1);
        buttonsGrid.add(btn2, 0, 2);
        buttonsGrid.add(btn3, 0, 3);
        buttonsGrid.add(btn4, 0, 4);
        buttonsGrid.add(btn5, 1, 0);
        buttonsGrid.add(btn6, 1, 1);
        buttonsGrid.add(btn7, 1, 2);
        buttonsGrid.add(btn8, 1, 3);
        buttonsGrid.add(btn9, 1, 4);
        buttonsGrid.add(btn10, 2, 0);
        buttonsGrid.add(btn11, 2, 1);
        buttonsGrid.add(btn12, 2, 2);
        buttonsGrid.add(btn13, 2, 3);
        buttonsGrid.add(btn14, 2, 4);
        buttonsGrid.add(btn15, 3, 0);
        buttonsGrid.add(btn16, 3, 1);
        buttonsGrid.add(btn17, 3, 2);
        buttonsGrid.add(btn18, 3, 3);
        buttonsGrid.add(btn19, 3, 4);

        List<Button> list = new ArrayList<>();
        list.add(btn);
        list.add(btn1);
        list.add(btn2);
        list.add(btn3);
        list.add(btn4);
        list.add(btn5);
        list.add(btn6);
        list.add(btn7);
        list.add(btn8);
        list.add(btn9);
        list.add(btn10);
        list.add(btn11);
        list.add(btn12);
        list.add(btn13);
        list.add(btn14);
        list.add(btn15);
        list.add(btn16);
        list.add(btn17);
        list.add(btn18);
        list.add(btn19);

        for (Button aList : list) {
            aList.setPrefSize(50, 20);
        }

        /*********** слушатель для  кнопок ***********/
        for (Button button : list) {
            String btnStr = button.getText();
            if (matchDigits(btnStr)) {
                button.setOnAction(event -> userTextField.setText(userTextField.getText() + btnStr));
            } else if (btnStr.equals("=")) {
                button.setOnAction(event -> userTextField.setText(Double.toString(new Calc().calculate(userTextField.getText()))));
            } else if (btnStr.equals("C")) {
                button.setOnAction((event -> userTextField.setText("")));
            } else if (matchNoDigits(btnStr)) {
                button.setOnAction(event -> userTextField.setText(userTextField.getText() + btnStr));
            }
        }
        /********  отрисовка окна **************/
        primaryStage.show();
    }

    public static void main(String[] args) {
      launch(args);
    }
}
