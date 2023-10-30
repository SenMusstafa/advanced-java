package com.etiya.campus.aj.mutlithreading;

import java.util.Collections;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    /*
    parametre olarak verdiğimiz permits kadar lock uygulanabilir
    Lockda tek thread locklayabiliyorken semaphore kullanıca permits kadar thread lock koyabilir
    Lockda lock ve unlock aynı threadde olmalı
    semaphroreda bir threadde acquire başka threadde release yapabiliyoruz
    birden fazla da acuire edebiliriz
    başta permits - verebiliriz
    dreainpermits ile tek seferde bütün permitleri alabiliriz
     */
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {
        Team team = new Team();
        team.doWork(new Code(500, "Task1"));
        team.doWork(new Code(200, "Task2"));
        team.doWork(new Code(300, "Task3"));
        team.doWork(new Code(400, "Task4"));
    }

    static class Team{
        public void doWork(Code code) throws InterruptedException {
            System.out.println("Team got job : " + code.getName() + " - " +code.getLines());
            semaphore.acquire();
            System.out.println("Acquired : " + code.getName());
            Thread t = new Thread(()->{
                try {
                    System.out.println("Coder got job : " + code.getName() + " - " +code.getLines());
                    Thread.sleep(code.getLines()*10);

                    semaphore.release();
                    System.out.println("Code written" + code.getName() + " - " + code.getLines());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }

    static class Code{
        private final int lines;
        private String name;

        public Code(int lines, String name){
            this.lines = lines;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLines(){
            return this.lines;
        }
    }
}
