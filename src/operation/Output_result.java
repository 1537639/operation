package operation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output_result {
    File exercise;
    File answer;

    public Output_result()
    {
        exercise=new File("src\\resourse\\exercise.txt");//题目文件
        answer=new File("src\\resourse\\answer.txt");//答案文件
    }

        public void output(String exercise_,String answer_,int num) throws IOException//输出题目文件和答案文件，每调用一次在txt后追加一条式子和一条答案
    {
        BufferedWriter ex_os=new BufferedWriter(new FileWriter(exercise,true));
        BufferedWriter an_os=new BufferedWriter(new FileWriter(answer,true));
        exercise_= num +","+exercise_+" =\r\n";
        answer_= num +","+answer_+"\r\n";

        ex_os.write(exercise_);
        an_os.write(answer_);
        ex_os.flush();
        an_os.flush();
        ex_os.close();
        an_os.close();

    }

    public void clearInfoForFile(String path) {
        File file = new File(path);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");  //写入空
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}