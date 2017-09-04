package com.hnxy.farmshop.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.hnxy.farmshop.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ContentView(R.layout.activity_chart)
public class ChartActivity extends BaseActivity {
    private static final String TAG = "chartActivity";
    @ViewInject(R.id.barChart)
    BarChart barChart;
    @ViewInject(R.id.lineChart)
    LineChart lineChart;
    @ViewInject(R.id.horizontalBarChart)
    HorizontalBarChart horizontalBarChart;
    @ViewInject(R.id.radarChart)
    RadarChart radarChart;
    @ViewInject(R.id.pieChart)
    PieChart pieChart;

    private List<ILineDataSet> points =new ArrayList<>();
    private List<Entry> xEntry = new ArrayList<>();
    private List<Entry> yEntry = new ArrayList<>();
    private List<Entry> yEntry2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPoints();
        lineChart.setEnabled(true);
        lineChart.setDrawGridBackground(false);
        lineChart.setPinchZoom(false);
        lineChart.setMaxVisibleValueCount(10);
        initX();
        initY();
        bindData();

        LineData lineData = new LineData(points);
        //将数据插入
        lineChart.setData(lineData);

        //设置动画效果
        lineChart.animateY(2000, Easing.EasingOption.Linear);
        lineChart.animateX(2000, Easing.EasingOption.Linear);
        lineChart.invalidate();
    }

    private void initY() {
        //获取右边的轴线
        YAxis rightAxis=lineChart.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        //获取左边的轴线
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        //设置网格线为虚线效果
//        leftAxis.disableAxisLineDashedLine();
//        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //是否绘制0所在的网格线
        leftAxis.setDrawZeroLine(false);
    }

    private void initX() {
        //        获取此图表的x轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(true);//设置轴启用或禁用 如果禁用以下的设置全部不生效
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        //xAxis.setTextSize(20f);//设置字体
        //xAxis.setTextColor(Color.BLACK);//设置字体颜色
        //设置竖线的显示样式为虚线
        //lineLength控制虚线段的长度
        //spaceLength控制线之间的空间
        xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.disableAxisLineDashedLine();
//        xAxis.setAxisMinimum(1f);//设置x轴的最小值
//        xAxis.setAxisMaximum(13f);//设置最大值
//        xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
////        xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
////        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        xAxis.setLabelCount(13);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.addLimitLine(new LimitLine(2,"月份"));
//        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                String s = value == 0f ? "" : ((int) value) + "月";
                return s;
            }
        });//格式化x轴标签显示字符
    }

    private void bindData() {
        LineDataSet yLineDataSet = new LineDataSet(yEntry,"");
        yLineDataSet.setCircleColor(Color.RED);
        yLineDataSet.setColor(Color.BLUE);
        yLineDataSet.setDrawValues(false);
        yLineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        points.add(yLineDataSet);

        LineDataSet yLineDataSet2 = new LineDataSet(yEntry2,"");
        yLineDataSet2.setCircleColor(Color.BLUE);
        yLineDataSet.setColor(Color.GREEN);
        yLineDataSet2.setDrawValues(false);
        yLineDataSet2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        points.add(yLineDataSet);
        points.add(yLineDataSet2);
    }

    private void getPoints() {
        for(int i=1;i<13;i++){
            yEntry.add(new Entry(i+0.0f,new Random().nextFloat()));
            yEntry2.add(new Entry(i+0.0f,new Random().nextFloat()));
        }
    }
}