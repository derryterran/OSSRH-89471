/**
 * 
 */
package io.github.derryterran;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author derry.liandana
 *
 */
public class TerranMain {
    public static void genIDSingle() {
    	System.out.println("gen ID Single");
    	Terranflake terranflake = new Terranflake(234);
        int iterations = 1;

        // Validate that the IDs 
        long[] ids = new long[iterations];
        for(int i = 0; i < iterations; i++) {
            ids[i] = terranflake.nextId();
        }

        for(int i = 0; i < ids.length; i++) {
            for(int j = i+1; j < ids.length; j++) {
                if(ids[i] == ids[j]) {
                	break;
                }
            }
        }
        System.out.println("IDS : "+ids.length);
        System.out.println("ids : "+ids[0]+" ");
    }
    public static void genIdMulti() throws InterruptedException, ExecutionException{
    	System.out.println("gen ID Multi");
        int numThreads = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        Terranflake terranflake = new Terranflake();
        int iterations = 2;

        // Validate that the IDs 
        Future<Long>[] futures = new Future[iterations];
        for(int i = 0; i < iterations; i++) {
            futures[i] =  executorService.submit(() -> {
                long id = terranflake.nextId();
                latch.countDown();;
                return id;
            });
        }
        System.out.println("IDS : "+futures.length);
        System.out.println("ids 1: "+futures[0].get());
        System.out.println("ids 2: "+futures[1].get());
        latch.await();
//        for(int i = 0; i < futures.length; i++) {
//            for(int j = i+1; j < futures.length; j++) {
//                if(!(futures[i].get() == futures[j].get())) {
//                	System.out.println("***** : "+futures[i].get());
//                	break;
//            }
//        }
//    }
        System.out.println("done");
    }
}
