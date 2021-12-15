package nhom13.covid.View.CachLy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nhom13.covid.Dao.CachLyDao;
import nhom13.covid.Model.CachLy;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.validation.ValidationSupport;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * @author trdat
 */
public class XemCachLy implements Initializable {
    private CachLyDao cachlyDao;

    @FXML
    private TableView<CachLy> cachLyTable;

    @FXML
    private TableColumn<CachLy, String> hoVaTenCol;

    @FXML
    private TableColumn<CachLy, Integer> maNhanKhauCol;
    @FXML
    private TableColumn<CachLy, Integer> mucDocol;

    @FXML
    private VBox chiTietVbox;

    @FXML
    private GridPane formCachLy;

    @FXML
    private TextField timKiemTextField;

    @FXML
    private ChoiceBox<String> timKiemChoiceBox;

    @FXML
    private ChoiceBox<String> ketQuaChoiceBox;

    @FXML
    public void timKiemButtonCLicked(ActionEvent actionEvent) {
        //Lấy kết quả cần tìm kiếm
        String timKiem = timKiemTextField.getText();
        //Lấy thuộc tính cần tìm kiếm theo
        String timKiemTheo = timKiemChoiceBox.getValue();
        if (timKiemTheo == null)
            return;

        Stream<CachLy> stream = CachLyDao.getAll().stream();

        //Truy vấn db lấy kết quả
        switch (timKiemTheo) {
            case "Họ và tên" -> stream = cachLyList.stream()
                    .filter(cachLy -> timKiem.equals(cachLy.getHoVaTen()));
            case "Mã nhân khẩu" -> stream = cachLyList.stream()
                    .filter(cachLy -> Integer.valueOf(timKiem).equals(cachLy.getMaNhanKhau()));
        }
        //Hiển thị kết quả
        cachLyList.setAll(stream.toList());
        cachLyTable.setItems(cachLyList);
    }

    @FXML
    void locButtonClicked(ActionEvent event) {
        Stream<CachLy> stream = cachlyDao.getAll().stream();

        

        //reset lại các thành phần giao diện
        timKiemChoiceBox.setValue(null);
        timKiemTextField.setText(null);
        chiTietVbox.setVisible(false);

        //Hiển thị kết quả
        cachLyList.setAll(stream.toList());
        cachLyTable.setItems(cachLyList);
    }

    @FXML
    void suaButtonClicked(ActionEvent event) {
        //Form test covid
        FormCachLy form = (FormCachLy) formCachLy.getChildren().get(0);

        ValidationSupport vs = form.getValidationSupport();
        if (vs.isInvalid()) {
            Notifications.create()
                    .title("Lỗi input")
                    .text("Mời nhập lại")
                    .position(Pos.TOP_RIGHT)
                    .hideAfter(Duration.seconds(5))
                    .showError();
            return;
        }


        //Lấy đối tượng được thay đổi
        CachLy cachLy = new CachLy();
        cachLy.setHoVaTen(form.getHoVaTen());
        cachLy.setMaNhanKhau(form.getMaNhanKhau());
        cachLy.setMucDo(form.getMucDo());
        cachLy.setTinhTrang(form.getTinhTrang());
        cachLy.setTdCachLy(form.getTdCachLy());
        cachLy.setKvCachLy(form.getKvCachLy());

        //Cập nhật db
        cachlyDao.update(cachLy);

        Notifications.create()
                .title("Thành công")
                .text("Sửa thông tin cách ly thành công")
                .position(Pos.TOP_RIGHT)
                .hideAfter(Duration.seconds(5))
                .showConfirm();

        //reset
        taiLaiClicked(null);
    }

    @FXML
    void xoaButonClicked(ActionEvent event) {
        //Lấy đối tượng
        FormCachLy form = (FormCachLy) formCachLy.getChildren().get(0);
        //Xóa trên db
        cachlyDao.delete(form.getMaNhanKhau());
        //Reset
        taiLaiClicked(null);
    }

    @FXML
    void taiLaiClicked(ActionEvent event) {
       cachLyList = FXCollections.observableArrayList(cachlyDao.getAll());
       cachLyTable.setItems(cachLyList);

        timKiemChoiceBox.setValue(null);
        timKiemTextField.setText(null);
        chiTietVbox.setVisible(false);
    }

    //Danh sách cách ly được hiển thị
    private ObservableList<CachLy> cachLyList;
    //Gợi ý cho thanh tìm kiếm
    private AutoCompletionBinding<String> goiY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cachlyDao = new CachLyDao();
        cachLyList = FXCollections.observableArrayList(cachlyDao.getAll());
        cachLyTable.setItems(cachLyList);

        hoVaTenCol.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
        maNhanKhauCol.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        
        chiTietVbox.setVisible(false);

        timKiemChoiceBox.getItems().setAll("Họ và tên",  "Mã nhân khẩu");
        timKiemChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oV, nV) -> {
            //Xóa hết gợi ý
            if (goiY != null)
                goiY.dispose();

            if (nV == null)
                return;

            //Thiết lập gợi ý
            switch (nV) {
                case "Họ và tên" -> goiY = TextFields.bindAutoCompletion(timKiemTextField,
                        cachLyList.stream().map(CachLy::getHoVaTen).toList());

                case "Mã nhân khẩu" -> goiY = TextFields.bindAutoCompletion(timKiemTextField,
                        cachLyList.stream().map(cachLy -> cachLy.getMaNhanKhau().toString()).toList());  
            }

            timKiemTextField.clear();
        });

        cachLyTable.setOnMouseClicked(event -> {
            CachLy cachLy = cachLyTable.getSelectionModel().getSelectedItem();
            if (cachLy == null)
                return;
            formCachLy.getChildren().setAll(new FormCachLy(cachLy));
            chiTietVbox.setVisible(true);
        });
    }

}
