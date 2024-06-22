package org.rezeda.diplomfx.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.rezeda.diplomfx.DB;
import org.rezeda.diplomfx.HelloApplication;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label error_text;

    @FXML
    private TextField input_link;

    @FXML
    private TextField input_small_link;

    @FXML
    private Label link;

    @FXML
    private VBox link_list;

    @FXML
    private Button btn;

    DB db = new DB();

    @FXML
    void initialize() throws SQLException, IOException, ClassNotFoundException {
        showLinks();
        btn.setOnAction(event ->{
            try {
                addLinkToDb();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addLinkToDb() throws SQLException, ClassNotFoundException, IOException {
        String text_link = input_link.getCharacters().toString();
        String small_link = input_small_link.getCharacters().toString();
        if(db.isExistSmallLink(small_link)){
            error_text.setText("Укажите другое сокращение.");
        } else {
            db.addLink(text_link, small_link);
            input_link.setText("");
            input_small_link.setText("");
            error_text.setText("Добавлено");
            link_list.getChildren().clear();
            showLinks();
        }
    }

    public void showLinks() throws SQLException, ClassNotFoundException, IOException {
        ResultSet resultSet = db.getLink();
        //Добавляю переменную для подсчета количества статей
        int count = 1;

        while(resultSet.next()) {
            Node node = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("show_link.fxml")));

            Label link = (Label) node.lookup("#link");
            link.setText(resultSet.getString("small_link"));

            link_list.getChildren().add(node);
            link_list.setSpacing(10);

            count++;
        }

    }


}
