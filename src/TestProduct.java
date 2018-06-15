import static java.lang.Thread.sleep;

public class TestProduct    {
    public static void main(String[] args) {
        WuTouStack ws= new WuTouStack();
        Product p= new Product(ws);
        Consumer c= new Consumer(ws);
        new Thread(p).start();
        new Thread(c).start();


    }


}
class WuTou{
    public WuTou(int id) {
        this.id = id;
    }

    int id;
    public String toString(){
       return "wutou="+id;
    }
}
class  WuTouStack{
    WuTou[] wutou= new WuTou[6];
    int index=0;
    public  synchronized void push(WuTou wt,int i){
        if(index ==6){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            this.notify();
            wutou[index]=wt;
            System.out.println("生产了第"+i+"个");
            index++;
    }
    public  synchronized WuTou pop(){
        if(index==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();
        index--;
        System.out.println("消费了第"+wutou[index]+"个");
        return wutou[index];
    }

}
class Product implements Runnable{

    public Product(WuTouStack ws) {
        this.ws = ws;
    }

    WuTouStack ws;
    @Override
    public void run() {
        for(int i=1;i<=20;i++){
            WuTou w= new WuTou(i);
            ws.push(w,i);

        }
    }
}
class Consumer implements Runnable{

    public Consumer(WuTouStack ws) {
        this.ws = ws;
    }

    WuTouStack ws;
    @Override
    public void run() {
        for(int i=1;i<=20;i++) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ws.pop();
        }
    }
}
