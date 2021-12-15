package nhom13.covid.View.CachLy;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nhom13.covid.Model.CachLy;
import nhom13.covid.Model.TestCovid;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.io.IOException;
import java.sql.Date;

/**
 * @author trdat
 */
public class FormCachLy extends GridPane {
    @FXML
    private TextField hoVaTenTextField;


    @FXML
    private TextField maNhanKhauTextField;

    @FXML
    private TextField mucDoTextField;

    
    @FXML
    private ChoiceBox<String> tinhTrangChoiceBox;

    @FXML
    private DatePicker tdCachLytDatePicker;
    @FXML
    private TextField kvCachLyTextField;
    

    private ValidationSupport validationSupport;

    private final String[] tinhTrangArr = {" Cách ly", "Hết cách ly"};

    public void clear() {
        hoVaTenTextField.clear();
        maNhanKhauTextField.clear();
        mucDoTextField.clear();
        tinhTrangChoiceBox.setValue(null);
        tdCachLytDatePicker.setValue(null);
        kvCachLyTextField.clear();
    }

    public FormCachLy(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormCachLy.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        tinhTrangChoiceBox.getItems().setAll(tinhTrangArr);

        validationSupport = new ValidationSupport();

        validationSupport.registerValidator(hoVaTenTextField, Validator.createEmptyValidator("Không được bỏ trống"));
        validationSupport.registerValidator(maNhanKhauTextField, Validator.createRegexValidator("Mã nhân khẩu phải là số nguyên", "\\d+", Severity.ERROR));
        validationSupport.registerValidator(mucDoTextField, Validator.createEmptyValidator("Mức độ phải là số nguyên"));
        validationSupport.registerValidator( tinhTrangChoiceBox, Validator.createEmptyValidator("Phải chọn kết quả"));
        validationSupport.registerValidator(tdCachLytDatePicker, Validator.createEmptyValidator("Phải chọn ngày"));
        validationSupport.registerValidator(kvCachLyTextField, Validator.createEmptyValidator("Không được bỏ trống"));

    }

    public FormCachLy(CachLy cachLy) {
        this();
        setHoVaTen(cachLy.getHoVaTen());
        setMaNhanKhau(cachLy.getMaNhanKhau());
        setMucDo(cachLy.getMucDo());
        setTinhTrang(cachLy.getTinhTrang());
        setTdCachLy(cachLy.getTdCachLy());
        setKvCachly(cachLy.getKvCachLy());
        maNhanKhauTextField.setEditable(false);
    }

    public ValidationSupport getValidationSupport() {
        return validationSupport;
    }

    private StringProperty hoVaTenProperty(){
        return hoVaTenTextField.textProperty();
    }

    public String getHoVaTen() {
        return hoVaTenProperty().get();
    }

    public void setHoVaTen(String hoVaTen) {
        hoVaTenProperty().setValue(hoVaTen);
    }

    private StringProperty maNhanKhauProperty() {
        return maNhanKhauTextField.textProperty();
    }

    public Integer getMaNhanKhau() {
        return Integer.valueOf(maNhanKhauProperty().get());
    }

    public void setMaNhanKhau(Integer maNhanKhau) {
        maNhanKhauProperty().setValue(maNhanKhau.toString());
    }

    private StringProperty mucDoProperty() {
        return mucDoTextField.textProperty();
    }

    public Integer getMucDo() {
        return Integer.valueOf(mucDoProperty().get());
    }

    public void setMucDo(Integer mucDo ) {
    	mucDoProperty().setValue(mucDo.toString());
    }

    private ObjectProperty<String> tinhTrangProperty() {
        return tinhTrangChoiceBox.valueProperty();
    }

    public Boolean getTinhTrang() {
        return tinhTrangProperty().get().equals(tinhTrangArr[0]);
    }

    public void setTinhTrang(Boolean tinhTrang) {
        tinhTrangProperty().setValue(tinhTrang ? tinhTrangArr[0] :tinhTrangArr[1]);
    }

    public Date getTdCachLy() {
        return Date.valueOf(tdCachLytDatePicker.getValue());
    }

    public void setTdCachLy(Date tdCachLy) {
    	tdCachLytDatePicker.setValue(tdCachLy.toLocalDate());
    }
    private StringProperty kvCachLyProperty() {
        return kvCachLyTextField.textProperty();
    }

    public String getKvCachLy() {
        return kvCachLyProperty().get();
    }

    public void setKvCachly(String kvCachLy) {
        kvCachLyProperty().setValue(kvCachLy);
    }
}
