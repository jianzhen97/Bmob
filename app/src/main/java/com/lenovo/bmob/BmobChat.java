package com.lenovo.bmob;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BmobChat extends AppCompatActivity {
    List<XBean> chartx = new ArrayList<>();
    List<Ybean> charty = new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            lineView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query();

    }

    public void query() {
        BmobQuery<XBean> query = new BmobQuery<>();

        query.findObjects(new FindListener<XBean>() {
            @Override
            public void done(List<XBean> list, BmobException e) {
                chartx = list;
                Log.e("size", chartx.size()+"");

            }
        });

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        BmobQuery<Ybean> query2 = new BmobQuery<>();
        query2.findObjects(new FindListener<Ybean>() {
            @Override
            public void done(List<Ybean> list, BmobException e) {
                for (Ybean ybeans:list
                     ) {
                    Log.e("ssd", ybeans.getValue());
                }
                charty = list;
                handler.sendEmptyMessage(0);
            }
        });

    }


    public void lineView() {
        //同样是需要数据dataset和视图渲染器renderer
        XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
        XYSeries series = new XYSeries("第一条线");

        int x1 = Integer.valueOf(chartx.get(0).getValue());
        int x2 = Integer.valueOf(chartx.get(1).getValue());
        int x3 = Integer.valueOf(chartx.get(2).getValue());
        int x4 = Integer.valueOf(chartx.get(3).getValue());
        int x5 = Integer.valueOf(chartx.get(4).getValue());
        int x6 = Integer.valueOf(chartx.get(5).getValue());

        series.add(1, x1);
        series.add(2, x2);
        series.add(3, x3);
        series.add(4, x4);
        series.add(5, x5);
        series.add(6, x6);
        mDataset.addSeries(series);
        XYSeries seriesTwo = new XYSeries("第二条线");

        int y1 = Integer.valueOf(charty.get(0).getValue());
        int y2 = Integer.valueOf(charty.get(1).getValue());
        int y3 = Integer.valueOf(charty.get(2).getValue());
        int y4 = Integer.valueOf(charty.get(3).getValue());
        int y5 = Integer.valueOf(charty.get(4).getValue());
        int y6 = Integer.valueOf(charty.get(5).getValue());

        seriesTwo.add(1, y1);
        seriesTwo.add(2, y2);
        seriesTwo.add(3, y3);
        seriesTwo.add(4, y4);
        seriesTwo.add(5, y5);
        seriesTwo.add(6, y6);


        mDataset.addSeries(seriesTwo);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        //设置图表的X轴的当前方向
        mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        mRenderer.setXTitle("日期");//设置为X轴的标题
        mRenderer.setYTitle("价格");//设置y轴的标题
        mRenderer.setAxisTitleTextSize(20);//设置轴标题文本大小
        mRenderer.setChartTitle("价格走势图");//设置图表标题
        mRenderer.setChartTitleTextSize(30);//设置图表标题文字的大小
        mRenderer.setLabelsTextSize(18);//设置标签的文字大小
        mRenderer.setLegendTextSize(20);//设置图例文本大小
        mRenderer.setPointSize(10f);//设置点的大小
        mRenderer.setYAxisMin(0);//设置y轴最小值是0
        mRenderer.setYAxisMax(15);
        mRenderer.setYLabels(10);//设置Y轴刻度个数（貌似不太准确）
        mRenderer.setXAxisMax(5);
        mRenderer.setShowGrid(true);//显示网格
        //将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月
        mRenderer.addXTextLabel(1, "1月");
        mRenderer.addXTextLabel(2, "2月");
        mRenderer.addXTextLabel(3, "3月");
        mRenderer.addXTextLabel(4, "4月");
        mRenderer.addXTextLabel(5, "5月");
        mRenderer.addXTextLabel(6, "6月");

        mRenderer.setXLabels(0);//设置只显示如1月，2月等替换后的东西，不显示1,2,3等
        mRenderer.setMargins(new int[]{20, 30, 15, 20});//设置视图位置

        // 第一条线
        XYSeriesRenderer r = new XYSeriesRenderer();//(类似于一条线对象)
        r.setColor(Color.BLUE);//设置颜色
        r.setPointStyle(PointStyle.CIRCLE);//设置点的样式
        r.setFillPoints(true);//填充点（显示的点是空心还是实心）
        r.setDisplayChartValues(true);//将点的值显示出来
        r.setChartValuesSpacing(10);//显示的点的值与图的距离
        r.setChartValuesTextSize(25);//点的值的文字大小

        //  r.setFillBelowLine(true);//是否填充折线图的下方
        //  r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
        r.setLineWidth(3);//设置线宽
        mRenderer.addSeriesRenderer(r);

        // 第二条线
        XYSeriesRenderer rTwo = new XYSeriesRenderer();//(类似于一条线对象)
        rTwo.setColor(Color.GRAY);//设置颜色
        rTwo.setPointStyle(PointStyle.CIRCLE);//设置点的样式
        rTwo.setFillPoints(true);//填充点（显示的点是空心还是实心）
        rTwo.setDisplayChartValues(true);//将点的值显示出来
        rTwo.setChartValuesSpacing(30);//显示的点的值与图的距离
        rTwo.setChartValuesTextSize(65);//点的值的文字大小

        //  rTwo.setFillBelowLine(true);//是否填充折线图的下方
        //  rTwo.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
        rTwo.setLineWidth(12);//设置线宽

        mRenderer.addSeriesRenderer(rTwo);


        GraphicalView view = ChartFactory.getLineChartView(this, mDataset, mRenderer);
        view.setBackgroundColor(Color.BLACK);
        setContentView(view);

    }
}
