package ro.tuc.ds2020.rbq;

public class ThreaderStarter {
    public static void mainReceiver()
{

    ThreadReceiver object1 = new ThreadReceiver();
    ThreadReceiver object2= new ThreadReceiver();

    object1.setThreadNr(1);
    object2.setThreadNr(2);

    object1.start();
    object2.start();

}
}
