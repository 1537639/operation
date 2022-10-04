package operation;

import sun.applet.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mainclass {
    public static void main(String[] args) throws IOException {
        try {
            if (args[0].equals("-n") && args[2].equals("-r")) {

                System.out.println("生成题目个数：" + args[1]);
                System.out.println("生成数范围：" + args[3]);
                int exercise_num = Integer.parseInt(args[1]);//生成题目个数
                int limit_num = Integer.parseInt(args[3]);//界定生成数范围
                Generate_exercise g_e = new Generate_exercise();//生成题目类
                Output_result o_r = new Output_result();//输出结果到exercise.txt和answer.txt类

                TreeNode tree;//公式树
                String exercise;//一个题目
                List<String> exercises = new ArrayList<>();//题目查重列表
                String answer;//一个答案

                //进行n次以下操作
                o_r.clearInfoForFile("src\\resourse\\exercise.txt");
                o_r.clearInfoForFile("src\\resourse\\answer.txt");
                for (int i = 1; i <= exercise_num; i++) {
                    exercise = g_e.generate(limit_num);//单次题目生成
                    //exercise="( 17 - 14 ) / ( 18 * 19 ) ";
                    String[] exc = TreeNode.toStringArrayTrimOutParrenthes(exercise);//
                    tree = new TreeNode(exc);
                    Num resultNum = tree.calculate();
                    Operate.simplify(resultNum);
                    if (resultNum.numer / resultNum.denomin > 1000)//生成的答案如超过1000，则丢弃
                    {
                        i--;
                        continue;
                    }
                    answer = Operate.fraction(resultNum);
                    if (answer == null)//如果返回空值，说明答案是负数
                    {
                        i--;
                        continue;//扔掉该回答
                    }

                    if (Answer_result.IsRepeat(exercises, tree))//如果重复
                    {
                        i--;
                        continue;//扔掉该回答
                    } else//否则加入该回答到查重列表
                    {
                        String dup = Answer_result.toPostifixExp(tree);
                        dup = Answer_result.getDupExpression(dup);
                        exercises.add(dup);
                    }
                    o_r.output(exercise, answer, i);//输出该题目和答案到文件
                }

                System.out.println("输出题目和答案成功！");
            } else if (args[0].equals("-e") && args[2].equals("-a")) {
                //针对指定问题文件和答案文件进行匹配
                //判断答案是否正确，输出分数到grade.txt
                Mainclass.clearInfoForFile2("src\\resourse\\grade.txt");
                Check_result c_r = new Check_result(args[1], args[3]);//检查答案并输出到grade.txt类
                c_r.check();
                System.out.println("问题和答案匹配程序匹配完成！");
            } else {
                System.out.println("参数输入错误！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearInfoForFile2(String path) throws IOException {
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
