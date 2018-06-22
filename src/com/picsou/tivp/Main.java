package com.picsou.tivp;

import com.picsou.tivp.entities.City;
import com.picsou.tivp.entities.Station;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static double tripRatio;
    public static double userRatio;
    public static int distanceRatio;
    public static long timestamp;


    public static void main(String[] args) {

        System.setProperty("org.lwjgl.librarypath", new File("slick").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("slick").getAbsolutePath());

        Scanner scanner = new Scanner(System.in);


        do {
            System.out.printf("Number of times the trips database should be used : ");
            tripRatio = scanner.nextDouble();
//            tripRatio = 1;
        } while (tripRatio <= 0);

        do {
            System.out.printf("Percentage of users accepting to be redirected to another station : ");
            userRatio = scanner.nextDouble();
//            userRatio = 0;
        } while (userRatio < 0 && userRatio > 100);

        // Maximal distance between two neighbor stations
        do {
            System.out.printf("Maximal distance between two stations so that one could redirect the user to the other : ");
            distanceRatio = scanner.nextInt();
//            distanceRatio = 300;
        } while (distanceRatio < 0);



        try {
            AppGameContainer appGameContainer = new AppGameContainer(new UserInterface());
            appGameContainer.setDisplayMode(UserInterface.WIDTH, UserInterface.HEIGHT, false);
            appGameContainer.setShowFPS(false);
            appGameContainer.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }



    public static City loadParis(DatabaseConfig databaseConfig) {
        List<Station> stations = databaseConfig.getParisStation();


        City paris = new City("Paris", 48.84681513232972, 2.376929360592796);


        Map<Integer, Station> stationMap = new HashMap<>();

        stations.forEach(station -> {
            stationMap.put(station.getId(), station);
        });

        paris.setStations(stationMap);


        final int[] totalNeighbord = {0};
        paris.getStations().forEach((id, station) -> {
            station.setNeighbords(paris.findNearestStations(station, distanceRatio));
            totalNeighbord[0] += station.getNeighbordsAmount();
        });
        System.out.println("There is " + (totalNeighbord[0] / (float) paris.getStations().size() + " neighbords in average"));




        paris.loadTourism();
        paris.loadRoads();

        return paris;
    }

}
