package nhom13.covid.View.CachLy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import nhom13.covid.Dao.CachLyDao;
import nhom13.covid.Model.CachLy;

import java.net.URL;
import java.time.YearMonth;
import java.util.*;

public class ThongKeCachLy implements Initializable {
    @FXML
    private BarChart<Integer, String> thangBarChart;

    @FXML
    private BarChart<String, Integer> mucdoBarChart;

    private CachLyDao  cachLyDao = new CachLyDao();

    private List<CachLy> list;

    public void createChartmucdo() {
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMucDo() ==0) {
                a++;
            } else if (list.get(i).getMucDo() ==1 ) {
                b++;
            } else if (list.get(i).getMucDo()==2 ) {
                c++;
            } else if (list.get(i).getMucDo() == 3 ) {
                d++;
            } 
        }

        XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();

        dataSeries.setName("Số lượng");
        dataSeries.getData().add(new XYChart.Data<>("F0", a));
        dataSeries.getData().add(new XYChart.Data<>("F1", b));
        dataSeries.getData().add(new XYChart.Data<>("F2", c));
        dataSeries.getData().add(new XYChart.Data<>("F3", d));
        
        mucdoBarChart.getXAxis().setLabel("mức độ");

        mucdoBarChart.getData().add(dataSeries);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list = cachLyDao.getAll();
        createChartmucdo();
    }
}
