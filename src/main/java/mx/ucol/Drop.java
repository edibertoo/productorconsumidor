package mx.ucol;

public class Drop {

    private String messages[] = new String[15];
    private  boolean isEmpty = true;
    private boolean isFull = false;
    private int currentPos = 0;

    public synchronized String take() {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Someone interrupted this thread." + e);
            }
        }

        String response = messages[currentPos - 1];
        currentPos --;

        if(currentPos ==0){
            isEmpty = true;
        }
        isFull = false;
        notifyAll();

        return response;
    }

    public synchronized void put(String message) {
        while (isFull) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        messages[currentPos] = message;
        currentPos++;

        if(currentPos == 10){
            isFull = true;
        }
        isEmpty = false;
        notifyAll();

        /*
        empty = false;
        this.message = message;
        notifyAll();

         */
    }
}
