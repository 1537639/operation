package operation;

import java.io.*;

public class Check_result {
    File exercise;
    File answer;
    File grade;
    public Check_result()//构造函数
    {
        exercise=new File("src\\resourse\\exercise.txt");//题目文件
        answer=new File("src\\resourse\\answer.txt");//答案文件
        grade=new File("src\\resourse\\grade.txt");//分数文件
    }

    public void check() throws IOException//输入题目文件和答案文件，调用回答问题函数判断答案是否正确，输出grade文件
    {
        int grade_num=0;
        int wrong_num=0;
        int[] isCorrect =new int[10000];
        int i=1;
        BufferedReader ex_br = new BufferedReader(new InputStreamReader(new FileInputStream(exercise)));
        BufferedReader an_br = new BufferedReader(new InputStreamReader(new FileInputStream(answer)));
        BufferedWriter gr_os=new BufferedWriter(new FileWriter(grade));

        String str_e;
        String str_a;
        while (((str_e = ex_br.readLine ()) != null)&&((str_a=an_br.readLine())!=null)&&i< isCorrect.length) {
            String[] split1=str_e.split(".");
            str_e=split1[1];
            split1=str_a.split(".");
            str_a=split1[1];

            if(Answer_result.answer(str_e).equals(str_a))
            {
                grade_num++;
                isCorrect[i-1]=1;
            }else
            {
                wrong_num++;
                isCorrect[i-1]=0;
            }
            i++;
        }

        String output="Correct: "+String.valueOf(grade_num)+" (";
        for(int j=0;j<isCorrect.length;j++)
        {
            if(isCorrect[j]==1) {
                output = output + String.valueOf(j + 1);
            }
            if(j+1!=isCorrect.length)
            {
                output=output+",";
            }
        }
        output=output+")\r\nWrong :"+String.valueOf(wrong_num)+" (";
        for(int j=0;j<isCorrect.length;j++)
        {
            if(isCorrect[j]==0) {
                output = output + String.valueOf(j + 1);
            }
            if(j+1!=isCorrect.length)
            {
                output=output+",";
            }
        }
        output=output+")\r\n";
        gr_os.write(output);
        gr_os.flush();
        gr_os.close();
        ex_br.close();
        an_br.close();

    }
}
