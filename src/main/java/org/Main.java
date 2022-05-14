package org;

import org.algorithm.DuplicateFinderAlgorithm;
import org.model.Profile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);

        Boolean accepted = false;

        int profileCount = 2;
        Profile[] profiles = new Profile[profileCount];

        for(int i=0;i<profileCount; i++){
            System.out.println("Details for profile " + (i+1));
            Profile profile = new Profile();

            while(!accepted){
                System.out.println("Please enter the first name");
                String temp = null;
                try {
                    temp = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!temp.isBlank()){
                    profile.setFirstName(temp);
                    accepted = true;
                } else {
                    System.out.println("Please enter first name(mandatory)");
                }
            }
            accepted = false;

            while(!accepted){
                System.out.println("Please enter the last name");
                String temp = null;
                try {
                    temp = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!temp.isBlank()){
                    profile.setLastName(temp);
                    accepted = true;
                } else {
                    System.out.println("Please enter last name(mandatory)");
                }
            }
            accepted = false;

            while(!accepted){
                System.out.println("Please enter the email");
                String temp = null;
                try {
                    temp = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!temp.isBlank()){
                    profile.setEmail(temp);
                    accepted = true;
                } else {
                    System.out.println("Please enter email(mandatory)");
                }
            }
            accepted = false;

            while(!accepted){
                System.out.println("Please enter the class year");
                String temp = "";
                try {
                    temp = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(temp.isBlank()){
                   accepted = true;
                   profile.setClassYear(null);
                } else {
                    try{
                        int tempInt = Integer.parseInt(temp);
                        if(tempInt > 0){
                            accepted = true;
                            profile.setClassYear(tempInt);
                        } else {
                            System.out.println("Please enter the valid class year( >0 )");
                        }
                    } catch (NumberFormatException e){
                        accepted = false;
                        System.out.println("Please enter a valid number");
                    }
                }
            }
            accepted = false;

            while(!accepted){
                System.out.println("Please enter the birth date(yyyy/mm/dd)");
                String temp = null;
                try {
                    temp = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(temp.isBlank()){
                    accepted = true;
                    profile.setDate(null);
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    simpleDateFormat.setLenient(false);
                    Date date = null;
                    try {
                        date = simpleDateFormat.parse(temp);
                        accepted = true;
                    } catch (ParseException e) {
                        System.out.println("Please enter a valid date format");
                    }
                    profile.setDate(date);
                }
            }
            accepted = false;

            profiles[i] = profile;
        }

        DuplicateFinderAlgorithm duplicateFinderAlgorithm = new DuplicateFinderAlgorithm();

        duplicateFinderAlgorithm.duplicateFinder(profiles);
    }
}
