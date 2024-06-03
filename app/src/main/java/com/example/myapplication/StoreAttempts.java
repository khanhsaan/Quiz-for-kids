//package com.example.myapplication;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class StoreAttempts {
//    public static int cartoonPoint, animalPoint;
//    public static List<String> attemptList;
//
//    public StoreAttempts(){
//    }
//
//    public int getCartoonPoint(){
//        return cartoonPoint;
//    }
//
//    public int getAnimalPoint(){
//        return animalPoint;
//    }
//
//    public static void main(String[] args){
//        attemptList = new ArrayList<>();
//
//        AnimalResultActivity animalResultActivity = new AnimalResultActivity();
//        animalPoint = animalResultActivity.getOverall_score();
//        String s = String.format("“Animals” area - attempt started on 10-01-2023 20:00 – points earned %d", animalPoint);
//        attemptList.add(s);
//
//        CartoonResultActivity cartoonResultActivity = new CartoonResultActivity();
//        cartoonPoint = cartoonResultActivity.getOverall_score();
//        String s2 = String.format("“Cartoon” area - attempt started on 10-01-2023 20:00 – points earned %d", cartoonPoint);
//        attemptList.add(s2);
//    }
//
//    public List<String> getAttemptList(){
//        return attemptList;
//    }
//}
