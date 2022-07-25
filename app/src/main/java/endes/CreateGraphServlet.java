package endes;

import java.util.Arraylist;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jfreechart.JFreeChartFunctions;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class CreateGraph extends HttpServlet{
    JFreeChartFunctions jfc = new JFreeChartFunctions();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        drawLineGraph(request,response);
    }
    protected void drawLineGraph(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/png");
            ArrayList<ArrayList> ar = (ArrayList<ArrayList>) request.getSession().getAttribute("chart1");
            ArrayList ar1 = new ArrayList();
            ArrayList ar2 = new ArrayList();
            for(int i=0; i<ar.size(); i++) {
                ar1.add(ar.get(i).get(0));
                ar2.add(ar.get(i).get(1));
            }
            DefaultCategoryDataset ds_cat = jfc.createDS_LineChart(ar1,ar2,);
            JFreeChart chart=ChartFactory.createLineChart("人数遷移","時刻", "人数", ds_cat, PlotOrientation.VERTICAL, true, false, false);
            ServletOutputStream objSos=response.getOutputStream();
            ChartUtilities.writeChartAsJPEG(objSos, chart, 600, 400);
    }
}