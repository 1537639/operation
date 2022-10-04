package operation;

import org.junit.Test;

import java.io.IOException;

class MainclassTest {

    @Test
    public static void main(String[] args) {
        /*
        args[0] ="-n";
        args[1]="1000";
        args[2]="-r";
        args[3]="200";
         */
       /* args[0] ="-n";
        args[1]="1000";
        args[2]="-r";
        args[3]="200";
        */
     /*
     -n 100 -r 20
     -n 252 -r 52
     -n 1000 -r 2000
     -n 10000 -r 30
     @@ 100 -r 20
     -n 100 %% 20
     -e src\resourse\testexercise1.txt -a src\resourse\testanswer1.txt
     -e src\resourse\testexercise2.txt -a src\resourse\testexercise2.txt
     src\resource\grade.txt
      */
        try {
            Mainclass.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}