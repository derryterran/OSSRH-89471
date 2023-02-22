package org.terran.pyn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {
        System.out.println( "Get started" );
        int x=786;
        System.out.println(x<<12);
        TerranMain.genIDSingle();
    }
}
