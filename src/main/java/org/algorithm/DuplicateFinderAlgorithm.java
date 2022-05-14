package org.algorithm;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.model.Profile;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DuplicateFinderAlgorithm {

    ArrayList<String> consideredAttr = new ArrayList<>();
    ArrayList<String> skippedAttr = new ArrayList<>();
    ArrayList<String> notConsideredAttr = new ArrayList<>();
    Integer totalScore = 0;

    public void duplicateFinder(Profile[] profiles){
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable runnableTask1 = () -> {
            synchronized (totalScore){
                totalScore = totalScore + checkDupForFirstNameLastNameEmail(profiles[0].getFirstName() + profiles[0].getLastName() + profiles[0].getEmail(),
                        profiles[1].getFirstName() + profiles[1].getLastName() + profiles[1].getEmail());
            }
        };

        Runnable runnableTask2 = () -> {
            synchronized (totalScore){
                totalScore = totalScore + checkDupForDate(profiles[0].getDate(), profiles[1].getDate());
            }
        };

        Runnable runnableTask3 = () -> {
            synchronized (totalScore){
                totalScore = totalScore + checkDupForClass(profiles[0].getClassYear(), profiles[1].getClassYear());
            }

        };

        executor.execute(runnableTask1);
        executor.execute(runnableTask2);
        executor.execute(runnableTask3);

        executor.shutdown();

        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("score 4" + totalScore);
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("profile 1, profile 2, total match score : " + totalScore + ", " +
                "matching_attributes: " + consideredAttr + ", non_matching_attributes: " + notConsideredAttr + ", ignored_attributes: " + skippedAttr);


    }

    private Integer checkDupForFirstNameLastNameEmail(String nameEmail1, String nameEmail2){
        consideredAttr.add("firstName");
        consideredAttr.add("lastName");
        consideredAttr.add("email");
        return FuzzySearch.ratio(nameEmail1, nameEmail2) > 80 ? 1 : 0;
    }

    private Integer checkDupForDate(Date date1, Date date2){
        if(date1 != null && date2 != null){
            if(date1.equals(date2)){
                consideredAttr.add("date");
                return 1;
            } else {
                notConsideredAttr.add("date");
                return -1;
            }
        } else {
            skippedAttr.add("date");
            return 0;
        }
    }

    private Integer checkDupForClass(Integer class1, Integer class2){
        if(class1 != null && class2 != null){
            if(class1 == class2){
                consideredAttr.add("class");
                return 1;
            } else {
                notConsideredAttr.add("class");
                return -1;
            }
        } else {
            skippedAttr.add("class");
            return 0;
        }
    }

}
