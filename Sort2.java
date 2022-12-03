import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;


class Array<E> {
    private final Object[] objectarray;
    public final int length;

    public Array(int length)    {
        objectarray = new Object [length];
        this.length = length;
    }

    void set(int i, long l) {
        objectarray[i] = l;
    }

    void set2(int i, String l) {
        objectarray[i] = l;
    }
    @Override
    public String toString() {
        return Arrays.toString(objectarray);
    }

}

//@reference https://www.softwaretestinghelp.com/java-generic-array/

//@reference https://www.geeksforgeeks.org/java-program-for-odd-even-sort-brick-sort/
class Oddevensort{
    static boolean isSorted=false;

    public static void oesorteven(ArrayList<Double> cg)
    {
        if (!isSorted) {
            isSorted = true;
            Double temp = 0.0;

            // Perform Bubble sort on even indexed element
            for (int i = 0; i <= cg.size() - 2; i = i + 2) {
                if (cg.get(i) > cg.get(i+1)) {
                    temp = cg.get(i);
                    cg.set(i,cg.get(i+1));
                    // cg.get(i) = cg.get(i+1);
                    cg.set(i+1, temp);
                    // cg.get(i+1) = temp;
                    isSorted = false;
                }
            }
        }

        return;
    }

    public static void oesortodd(ArrayList<Double> cg)
    {
        if (!isSorted) {
            isSorted = true;
            Double temp = 0.0;

            // Perform Bubble sort on even indexed element
            for (int i = 1; i <= cg.size() - 2; i = i + 2) {
                if (cg.get(i) > cg.get(i+1)) {
                    temp = cg.get(i);
                    cg.set(i,cg.get(i+1));
                    // cg.get(i) = cg.get(i+1);
                    cg.set(i+1, temp);
                    // cg.get(i+1) = temp;
                    isSorted = false;
                }
            }
        }

        return;
    }

    public static void oesort2(ArrayList<Double> cg)
    {
        // System.out.println(77);
        boolean isSorted = false; // Initially array is unsorted

        while (!isSorted) {
            isSorted = true;
            Double temp = 0.0;

            // Perform Bubble sort on odd indexed element
            for (int i = 1; i <= cg.size() - 2; i = i + 2) {
                if (cg.get(i) > cg.get(i+1)) {
                    temp = cg.get(i);
                    cg.set(i,cg.get(i+1));
                    // cg.get(i) = cg.get(i+1);
                    cg.set(i+1, temp);
                    // cg.get(i+1) = temp;
                    isSorted = false;
                }
            }

            // Perform Bubble sort on even indexed element
            for (int i = 0; i <= cg.size() - 2; i = i + 2) {
                if (cg.get(i) > cg.get(i+1)) {
                    temp = cg.get(i);
                    cg.set(i,cg.get(i+1));
                    // cg.get(i) = cg.get(i+1);
                    cg.set(i+1, temp);
                    // cg.get(i+1) = temp;
                    isSorted = false;
                }
            }
        }

        return;
    }

}

class Multithreading implements Runnable{
    // private int[] a;
    private int maxThreadCount;
    // private int minSize;
    private ArrayList<Double> cg;
    private int size;

    public Multithreading(ArrayList<Double> cg, int maxThreadCount, int size) {
        this.cg = cg;
        this.maxThreadCount = maxThreadCount;
        this.size = size;
    }

    public int getmaxThreadCount(){
        return this.maxThreadCount;
    }

    public ArrayList<Double> getcg(){
        return this.cg;
    }

    public int getSize(){
        return this.size;
    }

    public void run() {
        // System.out.println(13);
        while(!Oddevensort.isSorted){
            // System.out.println(44);
            Oddevensort.oesorteven(cg);
            Oddevensort.oesortodd(cg);
            Oddevensort.oesorteven(cg);
            Oddevensort.oesortodd(cg);
            // System.out.println(14);
        }


        // System.out.println(55);
    }
}

public class Sort2 {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println();
        System.out.println();
        System.out.println("Time is in ns");
        System.out.println();
        // System.out.println(" size of array   multithread time   normal time");
        ArrayList<String> arr= new ArrayList<>();
        arr.add("size of arr");
        arr.add("multithread time");
        arr.add("normal time");
        double num;
        DecimalFormat df;

        Array<String> str_arr = new Array<>(3);

        for(int i=0;i<3;i++){
            str_arr.set2(i,arr.get(i));
        }

        System.out.println(str_arr);

        //System.out.println(1);

        ArrayList<Double> cgpa4= new ArrayList<>();
        BufferedWriter f4 = new BufferedWriter(new FileWriter("output4.txt"));
        for(int i=0;i<10000;i++){

            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa4.add(Double.parseDouble(df.format(num)));



            try{
                f4.write(Double.toString(cgpa4.get(i)));
                f4.newLine();

                
            }
            catch (IOException e){
                System.out.println(e);
            }
        }

        // System.out.println(cgpa4.toString());
        f4.close();

        ArrayList<Double> even = new ArrayList<>();

        for (int i = 1; i <= cgpa4.size() - 2; i = i + 2) {
            even.add(cgpa4.get(i));
        }

        ArrayList<Double> odd = new ArrayList<>();
        for (int i = 0; i <= cgpa4.size() - 2; i = i + 2) {
            odd.add(cgpa4.get(i));
        }
        //System.out.println(2);
        Multithreading left= new Multithreading(even, 2, even.size());
        Multithreading right= new Multithreading(odd, 2, odd.size());
        //System.out.println(3);
        Thread t1= new Thread(left);
        //System.out.println(4);
        Thread t2= new Thread(right);
        //System.out.println(5);

        //with multithreading
        long startTime1= System.nanoTime();

        //System.out.println(6);
        t1.start();
        //System.out.println(7);
        t2.start();
        //System.out.println(8);

        t1.join();
        //System.out.println(9);
        t2.join();
        // System.out.println(10);
        long endTime1 = System.nanoTime();

        //without multithreading

        ArrayList<Double> cgpa5= new ArrayList<>();
        for(int i=0;i<10000;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa5.add(Double.parseDouble(df.format(num)));

        }

        long startTime2 = System.nanoTime();
        Oddevensort.oesort2(cgpa4);
        Oddevensort.oesort2(cgpa5);
        long endTime2 = System.nanoTime();


        Array<Integer> int_arr1= new Array<>(3);
        int_arr1.set(0,cgpa4.size());
        int_arr1.set(1, (endTime1 - startTime1));
        int_arr1.set(2, (endTime2 - startTime2));

        System.out.println(int_arr1+" in ns");

        // System.out.println( cgpa4.size()+" " +(endTime1 - startTime1) +" "+(endTime2 - startTime2)+" in ms");
        // System.out.println(cgpa5.toString());

        //System.out.println(1);

        ArrayList<Double> cgpa2= new ArrayList<>();
        BufferedWriter f2 = new BufferedWriter(new FileWriter("output3.txt"));
        for(int i=0;i<1000;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa2.add(Double.parseDouble(df.format(num)));



            try{
                f2.write(Double.toString(cgpa2.get(i)));
                f2.newLine();
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        f2.close();

        //System.out.println(cgpa2.toString());


        ArrayList<Double> even1 = new ArrayList<>();

        for (int i = 1; i <= cgpa2.size() - 2; i = i + 2) {
            even1.add(cgpa2.get(i));
        }

        ArrayList<Double> odd1 = new ArrayList<>();
        for (int i = 0; i <= cgpa2.size() - 2; i = i + 2) {
            odd1.add(cgpa2.get(i));
        }
        //System.out.println(2);
        Multithreading left1= new Multithreading(even1, 2, even1.size());
        Multithreading right1= new Multithreading(odd1, 2, odd1.size());
        //System.out.println(3);
        Thread t11= new Thread(left1);
        //System.out.println(4);
        Thread t21= new Thread(right1);
        //System.out.println(5);

        //with multithreading
        startTime1= System.nanoTime();

        //System.out.println(6);
        t11.start();
        //System.out.println(7);
        t21.start();
        //System.out.println(8);

        t11.join();
        //System.out.println(9);
        t21.join();
        // System.out.println(10);
        endTime1 = System.nanoTime();

        //without multithreading

        ArrayList<Double> cgpa22= new ArrayList<>();
        for(int i=0;i<1000;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa22.add(Double.parseDouble(df.format(num)));

        }

        startTime2 = System.nanoTime();
        Oddevensort.oesort2(cgpa2);
        Oddevensort.oesort2(cgpa22);
        endTime2 = System.nanoTime();

        Array<Integer> int_arr2= new Array<>(3);
        int_arr2.set(0,cgpa2.size());
        int_arr2.set(1, (endTime1 - startTime1));
        int_arr2.set(2, (endTime2 - startTime2));

        System.out.println(int_arr2+" in ns");

        // System.out.println( cgpa2.size()+" " +(endTime1 - startTime1) +" "+(endTime2 - startTime2)+" in ms");
        // System.out.println(cgpa22.toString());

        //System.out.println(1);

        ArrayList<Double> cgpa3= new ArrayList<>();
        BufferedWriter f41 = new BufferedWriter(new FileWriter("output2.txt"));
        for(int i=0;i<100;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa3.add(Double.parseDouble(df.format(num)));



            try{
                

                f41.write(Double.toString(cgpa3.get(i)));
                f41.newLine();

                
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        f41.close();

        //System.out.println(cgpa3.toString());


        ArrayList<Double> even2 = new ArrayList<>();

        for (int i = 1; i <= cgpa3.size() - 2; i = i + 2) {
            even2.add(cgpa3.get(i));
        }

        ArrayList<Double> odd2 = new ArrayList<>();
        for (int i = 0; i <= cgpa3.size() - 2; i = i + 2) {
            odd2.add(cgpa3.get(i));
        }
        //System.out.println(2);
        Multithreading left2= new Multithreading(even2, 2, even2.size());
        Multithreading right2= new Multithreading(odd2, 2, odd2.size());
        //System.out.println(3);
        Thread t112= new Thread(left2);
        //System.out.println(4);
        Thread t667= new Thread(right2);
        //System.out.println(5);

        //with multithreading
        startTime1= System.nanoTime();

        //System.out.println(6);
        t112.start();
        //System.out.println(7);
        t667.start();
        //System.out.println(8);

        t112.join();
        //System.out.println(9);
        t667.join();
        // System.out.println(10);
        endTime1 = System.nanoTime();

        //without multithreading

        ArrayList<Double> cgpa32= new ArrayList<>();
        // BufferedWriter f412 = new BufferedWriter(new FileWriter("output5.txt"));
        for(int i=0;i<100;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa32.add(Double.parseDouble(df.format(num)));
        }



        //     try{
                

        //         f412.write(Double.toString(cgpa32.get(i)));
        //         f412.newLine();

                
        //     }
        //     catch (IOException e){
        //         System.out.println(e);
        //     }
        // }
        // f412.close();

        startTime2 = System.nanoTime();
        Oddevensort.oesort2(cgpa3);
        Oddevensort.oesort2(cgpa32);
        endTime2 = System.nanoTime();

        Array<Integer> int_arr3= new Array<>(3);
        int_arr3.set(0,cgpa3.size());
        int_arr3.set(1, (endTime1 - startTime1));
        int_arr3.set(2, (endTime2 - startTime2));

        System.out.println(int_arr3+" in ns");

        // System.out.println( cgpa3.size()+" " +(endTime1 - startTime1) +" "+(endTime2 - startTime2)+" in ns");
        // System.out.println(cgpa32.toString());

        //System.out.println(1);

        ArrayList<Double> cgpa12= new ArrayList<>();
        BufferedWriter f51 = new BufferedWriter(new FileWriter("output6.txt"));
        for(int i=0;i<10;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa12.add(Double.parseDouble(df.format(num)));



            try{
                f51.write(Double.toString(cgpa12.get(i)));
                f51.newLine();

                
            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        f51.close();

        //System.out.println(cgpa12.toString());


        ArrayList<Double> even3 = new ArrayList<>();

        for (int i = 1; i <= cgpa12.size() - 2; i = i + 2) {
            even3.add(cgpa12.get(i));
        }

        ArrayList<Double> odd3 = new ArrayList<>();
        for (int i = 0; i <= cgpa12.size() - 2; i = i + 2) {
            odd3.add(cgpa12.get(i));
        }
        //System.out.println(2);
        Multithreading left3= new Multithreading(even3, 2, even3.size());
        Multithreading right4= new Multithreading(odd3, 2, odd3.size());
        //System.out.println(3);
        Thread t1121= new Thread(left3);
        //System.out.println(4);
        Thread t212= new Thread(right4);
        //System.out.println(5);

        //with multithreading
        startTime1= System.nanoTime();

        //System.out.println(6);
        t1121.start();
        //System.out.println(7);
        t212.start();
        //System.out.println(8);

        t1121.join();
        //System.out.println(9);
        t212.join();
        // System.out.println(10);
        endTime1 = System.nanoTime();

        //without multithreading

        ArrayList<Double> cgpa122= new ArrayList<>();
        for(int i=0;i<10;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa122.add(Double.parseDouble(df.format(num)));
        }

        startTime2 = System.nanoTime();
        Oddevensort.oesort2(cgpa12);
        Oddevensort.oesort2(cgpa122);
        endTime2 = System.nanoTime();

        Array<Integer> int_arr4= new Array<>(3);
        int_arr4.set(0,cgpa122.size());
        int_arr4.set(1, (endTime1 - startTime1));
        int_arr4.set(2, (endTime2 - startTime2));

        System.out.println(int_arr4+" in ns");

        // System.out.println( cgpa122.size()+" " +(endTime1 - startTime1) +" "+(endTime2 - startTime2)+" in ns");
        // System.out.println(cgpa122.toString());


        //System.out.println(1);

        ArrayList<Double> cgpa313= new ArrayList<>();
        BufferedWriter f516 = new BufferedWriter(new FileWriter("output8.txt"));
        for(int i=0;i<1;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa313.add(Double.parseDouble(df.format(num)));



            try{

                f516.write(Double.toString(cgpa313.get(i)));
                f516.newLine();

            }
            catch (IOException e){
                System.out.println(e);
            }
        }
        f516.close();

        //System.out.println(cgpa313.toString());


        ArrayList<Double> even4 = new ArrayList<>();

        for (int i = 1; i <= cgpa313.size() - 2; i = i + 2) {
            even4.add(cgpa313.get(i));
        }

        ArrayList<Double> odd4 = new ArrayList<>();
        for (int i = 0; i <= cgpa313.size() - 2; i = i + 2) {
            odd4.add(cgpa313.get(i));
        }
        //System.out.println(2);
        Multithreading left5= new Multithreading(even4, 2, even4.size());
        Multithreading right5= new Multithreading(odd4, 2, odd4.size());
        //System.out.println(3);
        Thread t1133= new Thread(left5);
        //System.out.println(4);
        Thread t45= new Thread(right5);
        //System.out.println(5);

        //with multithreading
        startTime1= System.nanoTime();

        //System.out.println(6);
        t1133.start();
        //System.out.println(7);
        t45.start();
        //System.out.println(8);

        t1133.join();
        //System.out.println(9);
        t45.join();
        // System.out.println(10);
        endTime1 = System.nanoTime();

        //without multithreading

        ArrayList<Double> cgpa3132= new ArrayList<>();
        for(int i=0;i<1;i++){
            
            num=Math.random() * (10.00 - 0.01 ) + 0.01;
            df= new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            cgpa3132.add(Double.parseDouble(df.format(num)));
        }

        startTime2 = System.nanoTime();
        Oddevensort.oesort2(cgpa313);
        Oddevensort.oesort2(cgpa3132);
        endTime2 = System.nanoTime();

        Array<Integer> int_arr= new Array<>(3);
        int_arr.set(0,cgpa313.size());
        int_arr.set(1, (endTime1 - startTime1));
        int_arr.set(2, (endTime2 - startTime2));

        System.out.println(int_arr+" in ns");

        // System.out.println( cgpa313.size()+" " +(endTime1 - startTime1) +" "+(endTime2 - startTime2)+" in ns");
        // System.out.println(cgpa313.toString());

        BufferedWriter ff1 = new BufferedWriter(new FileWriter("outputt1.txt"));
        BufferedWriter ff2 = new BufferedWriter(new FileWriter("outputt2.txt"));
        BufferedWriter ff3 = new BufferedWriter(new FileWriter("outputt3.txt"));
        BufferedWriter ff4 = new BufferedWriter(new FileWriter("outputt4.txt"));
        BufferedWriter ff5 = new BufferedWriter(new FileWriter("outputt5.txt"));

        for(int i=0;i<cgpa4.size();i++){
            try{

                ff1.write(Double.toString(cgpa4.get(i)));
                ff1.newLine();
    
            }
            catch (IOException e){
                System.out.println(e);
            }
            
        }
    ff1.close();

    for(int i=0;i<cgpa2.size();i++){
        try{

            ff2.write(Double.toString(cgpa2.get(i)));
            ff2.newLine();

        }
        catch (IOException e){
            System.out.println(e);
        }
        
    }
ff2.close();

for(int i=0;i<cgpa3.size();i++){
    try{

        ff3.write(Double.toString(cgpa3.get(i)));
        ff3.newLine();

    }

    catch (IOException e){
        System.out.println(e);
    }
    
}

ff3.close();

for(int i=0;i<cgpa12.size();i++){
    try{

        ff4.write(Double.toString(cgpa12.get(i)));
        ff4.newLine();

    }
    catch (IOException e){
        System.out.println(e);
    }
    
}
ff4.close();

for(int i=0;i<cgpa313.size();i++){
    try{

        ff5.write(Double.toString(cgpa313.get(i)));
        ff5.newLine();

    }
    catch (IOException e){
        System.out.println(e);
    }
    
}
ff5.close();

    }

}
