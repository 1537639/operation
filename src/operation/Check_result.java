package operation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Check_result {
    File exercise;
    File answer;
    File grade;
    public Check_result(String exercise_path,String answer_path)//构造函数
    {
        exercise=new File(exercise_path);//题目文件
        answer=new File(answer_path);//答案文件
        grade=new File("src\\resourse\\grade.txt");//分数文件
    }

    public void check() throws IOException//输入题目文件和答案文件，调用回答问题函数判断答案是否正确，输出grade文件
    {
        int grade_num=0;
        int wrong_num=0;
        List isCorrect=new ArrayList();
        BufferedReader ex_br;
        BufferedReader an_br;
        BufferedWriter gr_os;
        //int[] isCorrect =new int[10000];
        try {
             ex_br = new BufferedReader(new InputStreamReader(new FileInputStream(exercise)));
             an_br = new BufferedReader(new InputStreamReader(new FileInputStream(answer)));
             gr_os = new BufferedWriter(new FileWriter(grade));
        }
        catch (IOException e)
        {
            System.out.println("文件不存在");
            return;
        }

        String str_e;
        String str_a;
        while (((str_e = ex_br.readLine ()) != null)&&((str_a=an_br.readLine())!=null)) {
            String[] split1=str_e.split(",");
            str_e=split1[1];
            str_e=str_e.substring(0,str_e.length()-2);
            split1=str_a.split(",");
            str_a=split1[1];

            if(Answer_result.answer(str_e).equals(str_a))
            {
                grade_num++;
                isCorrect.add(1);
            }else
            {
                wrong_num++;
                isCorrect.add(0);
            }
        }

        String output="Correct: "+grade_num+" (";
        for(int j=0;j<isCorrect.size();j++)
        {
            if(isCorrect.get(j).equals(1)) {
                System.out.println("第"+j+"道题是正确的");
                output = output + String.valueOf(j + 1)+",";
            }
            else if(isCorrect.get(j).equals(0))
            {
                System.out.println("第"+j+"道题是错误的");
            }
            if(j+1==isCorrect.size()&&isCorrect.get(j).equals(1))
            {
                System.out.println("第"+(j+1)+"道题是正确的");
                output = output + String.valueOf(j + 1);
            }
            else if(j+1==isCorrect.size()&&isCorrect.get(j).equals(0))
            {
                System.out.println("第"+(j+1)+"道题是错误的");
            }
        }
        output=output+")\r\nWrong :"+wrong_num+" (";
        for(int j=0;j<isCorrect.size();j++)
        {
            if(isCorrect.get(j).equals(0)) {
                output = output + String.valueOf(j + 1)+",";
            }
            if(j+1==isCorrect.size()&&isCorrect.get(j).equals(0))
            {
                output = output + String.valueOf(j + 1);
            }
        }
        System.out.println("正确答案个数："+grade_num+"\r\n错误答案个数："+wrong_num);
        output=output+")\r\n";
        gr_os.write(output);
        gr_os.flush();
        gr_os.close();
        ex_br.close();
        an_br.close();

    }
}
