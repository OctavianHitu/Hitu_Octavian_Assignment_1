package ro.tuc.ds2020.rbq;

public class ThreadReceiver extends Thread {
    int threadNr=0;

    public void setThreadNr(int threadNr) {
        this.threadNr = threadNr;
    }

    public void run()
    {
        try {
            Receiver.main(threadNr);
        }
        catch (Exception e) {
            System.out.println("Exception");
        }
    }

}
