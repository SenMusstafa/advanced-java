package com.etiya.campus.aj.mutlithreading;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

/*
Taskların bir thread poolda otomatik olarak zamanlanması ile ilgilidir
Tasklar isteğe uygun olarak parçalara bölünebilir olmalıdır
tasklar yani işlenen birimler ForkJoinTask
ForkJoinTasklar ForkJoinPoolda zamanlanır scheduled
    her thrad için task listesi tutar biri bitince diğerini atar
    workstealing algorithm ile iş yığılan bir threadden müsait olana aktarılır

execute asenkron çalıştırır
invoke çağırır ve sonucu bekler
submit çağırır ve future döner

stream.paralelsteram de forkjoinframework kullanır
 */
public class ForkJoinPoolExample {
    private static int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(2);

        Future<?> future = pool.submit(new IncrementTask(0, 8));

        future.get();

        System.out.println("The array is: " + Arrays.toString(array));
    }
    static class IncrementTask extends RecursiveAction {
        private final int left;
        private final int right;

        public IncrementTask(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left < 3) {
                for (int i = left; i < right; i++) {
                    array[i]++;
                }
            } else {
                int mid = (left + right) / 2;
                invokeAll(new IncrementTask(left, mid), new IncrementTask(mid, right));
            }
        }
    }
}
