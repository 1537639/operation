package operation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Output_result {
    File exercise;
    File answer;

    public Output_result()
    {
        exercise=new File("src\\resourse\\exercise.txt");//题目文件
        answer=new File("src\\resourse\\answer.txt");//答案文件

    }

    public void output(String exercise_,String answer_,int num)//输出题目文件和答案文件，每调用一次在txt后追加一条式子和一条答案
    {
        BufferedWriter ex_os=new BufferedWriter(new FileWriter(exercise));
        BufferedWriter ex_os=new BufferedWriter(new FileWriter(exercise));
    }
}
