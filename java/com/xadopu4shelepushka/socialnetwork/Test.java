package com.xadopu4shelepushka.socialnetwork;

import com.xadopu4shelepushka.socialnetwork.models.User;
import lombok.Data;

import javax.management.timer.Timer;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Test {
    public static void main(String[] args) {




        List<Integer> list = new LinkedList<>();


        PriorityQueue<String> queue = new PriorityQueue<>();

    }

    public class MyCollection<T> extends ArrayDeque<T>{



    }

    public static class DateInterval extends Pair<LocalDate>{



        public DateInterval(LocalDate first, LocalDate second) {
            super(first, second);
        }
    }

    @Data
    public static class Pair<T>{
        public T first;
        public T second;

        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class CoolBird implements Comparable<CoolBird>{
        @Override
        public int compareTo(CoolBird o) {
            return 0;
        }
    }

    public static class Outer{
        private int aOuter;

        public Outer(int aOuter) {

            this.aOuter = aOuter;

        }

        public void method(){
            class Pair{
                int a;
                int b;

                public Pair(int a, int b) {
                    this.a = a;
                    this.b = b;
                }
            }

            Pair pair = new Pair(1,1);

        }

        public class Inner{
            private int aInner;

            public Inner(int aInner) {
                aOuter = 5;
                this.aInner = aInner;
            }
        }
    }


    public static void someMethod(IntConsumer a){
        a.accept(5);
    }

    public static abstract class Alive implements AutoCloseable {
        protected int breaths;

        private final String aaa = "aaa";

        public Alive(int breaths) {
            this.breaths = breaths;
        }

        @Override
        public void close() throws Exception {
            System.out.println("Closing");
        }

        public abstract void sound(String text);
    }






    public interface Dino{

        private int inT(){
            return 1;
        }        
        
        private void pet() {
            //
            inT();
            
            //
            
            
            //
            
        }

         int drive();
    }

    public interface Diamond{
        default void drive(){
            System.out.println("diamond drive");
        }
    }







    public static class Bird extends Alive {

        private int wings;

        public final static int dino = 1;
        public int dinoNotStatic = 1;

        public Bird(int breaths, int wings) {
            super(breaths);
            this.wings = wings;
            System.out.println("constructor Bird");


        }

        @Override
        public String toString() {
            return "Bird{" +
                    "breaths=" + breaths +
                    ", wings=" + wings +
                    '}';
        }



        public String toString(int a) {
            return "Bird{" +
                    " wings=" + wings +
                    '}';
        }

        @Override
        public void sound(String text) {
            //кричу в файлик
        }
    }
}
