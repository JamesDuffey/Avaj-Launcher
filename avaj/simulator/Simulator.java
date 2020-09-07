package simulator;

import vehicles.AircraftFactory;
import vehicles.Flyable;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;
import java.util.*;
import java.io.*;
import tower.WeatherTower;

public class Simulator {
    public static WeatherTower weatherTower = new WeatherTower();
    public static void main(String[] args){
      PrintStream fileStream;
      PrintStream originalOut = System.out;
      try {
        fileStream = new PrintStream("simulation.txt");
        System.setOut(fileStream);
      } catch (FileNotFoundException e1) {
        System.out.println("There was an error while trying to write to simulation.txt");
        e1.printStackTrace();
      }
      
      int sim = 0;
      try {
        if (args.length == 0) {
          System.out.println("No file passed.");
          System.exit(0);
        }
        else {
          File scenario = new File(args[0]);
          Scanner myReader = new Scanner(scenario);

          if (!myReader.hasNextLine()){
            customExit("File is not supported or empty!", originalOut, myReader);
          }
          
          while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] arr = data.split(" ");
            if (arr.length == 1){
              try{
                sim = Integer.parseInt(arr[0]);
                if (sim <= 0){
                  customExit("Simulations can't be 0 or negative please make it positive!", originalOut, myReader);
                }
              } catch (Exception e) {
                customExit("Simulations is not correct! Please make it a number.", originalOut, myReader);
              }
            }
            else if (arr.length == 5){
              try {
                Flyable aircraft = AircraftFactory.newAircraft(arr[0], arr[1], Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
                aircraft.registerTower(weatherTower);
              }
              catch (Exception e){
                customExit("Please check the format of your aircraft. Format: Type ID Longitude Latitude Height", originalOut, myReader);
              }
            }
            else {
              customExit("File is not formated correctly!", originalOut, myReader);
            }
          }

          myReader.close();
          for (int i = 1; i <= sim; i++) {
            System.out.println("");
            System.out.println("Simulation " + i);
            weatherTower.changeWeather();
          }
          System.setOut(originalOut);
        }
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    private static void customExit(String message, PrintStream originalOut, Scanner myReader){
      System.setOut(originalOut);
      System.out.println(message);
      myReader.close();
      System.exit(0);
    }
}