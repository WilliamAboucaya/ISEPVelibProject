package com.picsou.tivp;

import com.picsou.tivp.entities.City;
import org.lwjgl.opengl.GREMEDYFrameTerminator;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Stats {

    public static float GRAPH_SIZER = 10;

    private List<Integer> movingBikes;
    private List<Integer> storageBikes;
    private List<Integer> onStations;

    private List<Integer> problemStations;
    private List<Integer> goodStations;

    private List<Integer> emptys;
    private List<Integer> lows;
    private List<Integer> goods;
    private List<Integer> highs;
    private List<Integer> fulls;

    private List<Integer> ticks;





    private int lastMoving, lastStorage, lastOnStation, lastProblemStation,  lastGoodStation;

    private int lastEmpty;
    private int lastLow;
    private int lastGood;
    private int lastHigh;
    private int lastFull;

    private long lastTimestamp;
    private long averageUnavailabilityTime;

    private PrintWriter printWriter;


    public Stats() {
        movingBikes = new ArrayList<>();
        storageBikes = new ArrayList<>();
        onStations = new ArrayList<>();
        problemStations = new ArrayList<>();
        goodStations = new ArrayList<>();

        emptys = new ArrayList<>();
        lows = new ArrayList<>();
        goods = new ArrayList<>();
        highs = new ArrayList<>();
        fulls = new ArrayList<>();

        ticks = new ArrayList<>();

        lastTimestamp = 0;
        averageUnavailabilityTime = 0;

        File outputs = new File("outputs/");
        if (!outputs.exists()) {
            outputs.mkdir();
        }


        long currentTimestamp = new Date().getTime();
        try {
            printWriter = new PrintWriter("outputs/outputs_" + currentTimestamp + "_" + Main.userRatio + "_" + Main.tripRatio + "_" + Main.distanceRatio + ".csv");
            printWriter.println("currentTimestamp" + "," + "moving" + "," + "storage" + "," + "onStation" + "," + "problemStation" + "," + "goodStation" + "," + "empty" + "," + "low" + "," + "good" + "," + "high" + "," + "full");

//            printWriter = new PrintWriter("outputs/outputs_" + currentTimestamp + "_" + Main.userRatio + "_" + Main.tripRatio + "_" + Main.distanceRatio + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * Stores in the Stats object the data given as parameters
     * Re-calculates the value of the average unavailability time
     *
     * @param moving
     * @param storage
     * @param onStation
     * @param problemStation
     * @param goodStation
     * @param empty
     * @param low
     * @param good
     * @param high
     * @param full
     * @param currentTimestamp
     */

    public void addStat(int moving, int storage, int onStation, int problemStation, int goodStation, int empty, int low, int good, int high, int full, long currentTimestamp) {
        movingBikes.add(moving);
        storageBikes.add(storage);
        onStations.add(onStation);
        problemStations.add(problemStation);
        goodStations.add(goodStation);

        emptys.add(empty);
        lows.add(low);
        goods.add(good);
        highs.add(high);
        fulls.add(full);




        this.lastMoving = moving;
        this.lastStorage = storage;
        this.lastOnStation = onStation;
        this.lastProblemStation = problemStation;
        this.lastGoodStation = goodStation;

        this.lastEmpty = empty;
        this.lastLow = low;
        this.lastGood = good;
        this.lastHigh = high;
        this.lastFull = full;

        averageUnavailabilityTime += (currentTimestamp - lastTimestamp) / 1000 * lastProblemStation / (lastGoodStation + lastProblemStation);



         printWriter.println(currentTimestamp + "," + moving + "," + storage + "," + onStation + "," + problemStation + "," + goodStation + "," + empty + "," + low + "," + good + "," + high + "," + full);

//        printWriter.println("{\"index\":{\"_index\":\"velib_" + Main.userRatio + "_" + Main.tripRatio + "_" + Main.distanceRatio + "\",\"_id\":" + currentTimestamp + "}}");
//
//        printWriter.println("{\"currentTimestamp\": " + currentTimestamp + ", \"moving\": " + lastMoving + ", \"storage\": " + lastStorage + ", \"onStation\": " + lastOnStation + ", \"problemStation\": " + lastProblemStation + ", \"goodStation\": " + lastGoodStation + ", \"empty\": " + lastEmpty + ", \"low\": " + lastLow + ", \"good\": " + lastGood + ", \"high\": " + lastHigh + ", \"full\": " + lastFull + "}");
//
//        printWriter.flush();




    }

    public List<Integer> getMovingBikes() { return movingBikes; }

    public void setMovingBikes(List<Integer> movingBikes) { this.movingBikes = movingBikes; }

    public List<Integer> getStorageBikes() { return storageBikes; }

    public void setStorageBikes(List<Integer> storageBikes) { this.storageBikes = storageBikes; }

    public List<Integer> getOnStations() { return onStations; }

    public void setOnStations(List<Integer> onStations) { this.onStations = onStations; }

    public List<Integer> getProblemStations() { return problemStations; }

    public void setProblemStations(List<Integer> problemStations) { this.problemStations = problemStations; }

    public List<Integer> getGoodStations() { return goodStations; }

    public void setGoodStations(List<Integer> goodStations) { this.goodStations = goodStations; }

    public static float getGraphSizer() { return GRAPH_SIZER; }

    public static void setGraphSizer(float graphSizer) { GRAPH_SIZER = graphSizer; }

    public List<Integer> getEmptys() { return emptys; }

    public void setEmptys(List<Integer> emptys) { this.emptys = emptys; }

    public List<Integer> getLows() { return lows; }

    public void setLows(List<Integer> lows) { this.lows = lows; }

    public List<Integer> getGoods() { return goods; }

    public void setGoods(List<Integer> goods) { this.goods = goods; }

    public List<Integer> getHighs() { return highs; }

    public void setHighs(List<Integer> highs) { this.highs = highs; }

    public List<Integer> getFulls() { return fulls; }

    public void setFulls(List<Integer> fulls) { this.fulls = fulls; }

    public List<Integer> getTicks() { return ticks; }

    public void setTicks(List<Integer> ticks) { this.ticks = ticks; }

    public int getLastMoving() { return lastMoving; }

    public void setLastMoving(int lastMoving) { this.lastMoving = lastMoving; }

    public int getLastStorage() { return lastStorage; }

    public void setLastStorage(int lastStorage) { this.lastStorage = lastStorage; }

    public int getLastOnStation() { return lastOnStation; }

    public void setLastOnStation(int lastOnStation) { this.lastOnStation = lastOnStation; }

    public int getLastProblemStation() { return lastProblemStation; }

    public void setLastProblemStation(int lastProblemStation) {
        this.lastProblemStation = lastProblemStation;
    }

    public int getLastGoodStation() {
        return lastGoodStation;
    }

    public void setLastGoodStation(int lastGoodStation) {
        this.lastGoodStation = lastGoodStation;
    }

    public int getLastEmpty() {
        return lastEmpty;
    }

    public void setLastEmpty(int lastEmpty) {
        this.lastEmpty = lastEmpty;
    }

    public int getLastLow() {
        return lastLow;
    }

    public void setLastLow(int lastLow) {
        this.lastLow = lastLow;
    }

    public int getLastGood() {
        return lastGood;
    }

    public void setLastGood(int lastGood) {
        this.lastGood = lastGood;
    }

    public int getLastHigh() {
        return lastHigh;
    }

    public void setLastHigh(int lastHigh) {
        this.lastHigh = lastHigh;
    }

    public int getLastFull() {
        return lastFull;
    }

    public void setLastFull(int lastFull) {
        this.lastFull = lastFull;
    }

    /**
     * Draws the graphs and infos for the stats stored in this object
     *
     * @param graphics the graphical container in which the stats will be drawn
     * @param tick
     * @param currentTimestamp
     * @param mainFormat
     */
    public void drawStat(Graphics graphics, int tick, long currentTimestamp, SimpleDateFormat mainFormat) {

        int x = 0;
        int y = UserInterface.HEIGHT - 200;
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        hourFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        ticks.add(tick);
        float tickWidth = UserInterface.WIDTH / (float) (3600 * 24);

        for (int it = 0; it < tick; it++) {
            graphics.setColor(Color.yellow);
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT - 150, tickWidth, - (movingBikes.get(it) / GRAPH_SIZER));

            graphics.setColor(Color.gray);
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT - (storageBikes.get(it) / GRAPH_SIZER) - 150, tickWidth, 2);

            float emptysHeight = -emptys.get(it) / GRAPH_SIZER;
            float lowsHeight = -lows.get(it) / GRAPH_SIZER;
            float goodsHeight = -goods.get(it) / GRAPH_SIZER;
            float highsHeight = -highs.get(it) / GRAPH_SIZER;
            float fullsHeight = -fulls.get(it) / GRAPH_SIZER;

            graphics.setColor(Color.decode("0x4B0082"));
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT, tickWidth, emptysHeight);

            graphics.setColor(Color.blue);
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT + emptysHeight, tickWidth, lowsHeight);

            graphics.setColor(Color.green);
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT + emptysHeight + lowsHeight, tickWidth, goodsHeight);

            graphics.setColor(Color.orange);
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT + emptysHeight + lowsHeight + goodsHeight, tickWidth, highsHeight);

            graphics.setColor(Color.red);
            graphics.fillRect(it * tickWidth, UserInterface.HEIGHT + emptysHeight + lowsHeight + goodsHeight + highsHeight, tickWidth, fullsHeight);
        }

        for (int it = 0; it < (3600 * 24); it++) {
            graphics.setColor(Color.white);
            if (it % 3600 == 0) {
                graphics.fillRect(it * tickWidth, UserInterface.HEIGHT - 120, 2, - 20);
                graphics.drawString(String.valueOf(it / 3600), it * tickWidth + 5, UserInterface.HEIGHT - 140);
            }
        }



        if (lastProblemStation != 0) {
            x = UserInterface.WIDTH - 250;
            y = 0;

            graphics.drawString(mainFormat.format(currentTimestamp), x, y);

            // graphics.setColor(Color.lightGray);
            // graphics.drawString(lastStorage + " in storage", x, y + 20);

            graphics.setColor(Color.red);
            graphics.drawString("Station NOK : " + lastProblemStation, x, y + 20);

            graphics.setColor(Color.green);
            graphics.drawString("Stations OK : " + lastGoodStation, x, y + 40);

//            graphics.setColor(Color.white);
//            float percentage;
//            if (lastProblemStation == 0) {
//                percentage = 0;
//            } else {
//                percentage = Math.round(lastProblemStation / (float) (lastGoodStation + lastProblemStation) * 10000f)/100f;
//            }
//            graphics.drawString("Problematic trips : " + percentage + "%", x, y + 60);

            graphics.setColor(Color.yellow);
            graphics.drawString(lastMoving + " moving", x, y + 80);

            // graphics.drawString("Bikes Generated : " + String.valueOf(City.generatedBikes), x, y + 120);

            graphics.setColor(Color.white);
            graphics.drawString("Average downtime : " + hourFormat.format(new Date(averageUnavailabilityTime)), x, y + 100);

            graphics.setColor(Color.yellow);
            graphics.drawString("Number of ongoing trips", x + 20, y + 600);
        }

        drawLegend(graphics);
    }

    /**
     * Draws the color code of the stations and state distribution
     *
     * @param graphics the graphical container in which the infos will be drawn
     */
    public void drawLegend(Graphics graphics) {
        float x = 10;
        float y = 500;

//        graphics.setColor(Color.white);
//        graphics.fillRect(x, y, 50, 19);
        graphics.setColor(Color.decode("0x4B0082"));
        graphics.drawString("Empty", x, y);

        graphics.setColor(Color.blue);
        graphics.drawString("Low ( < 10% ) ", x, y + 20);

        graphics.setColor(Color.green);
        graphics.drawString("Good ( 10% - 90% )", x, y + 40);

        graphics.setColor(Color.orange);
        graphics.drawString("High ( > 90% )", x, y + 60);

        graphics.setColor(Color.red);
        graphics.drawString("Full", x, y + 80);



    }
}
