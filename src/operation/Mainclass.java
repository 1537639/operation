package operation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Mainclass {
    public static void main(String[] args) throws IOException {
        Generate_exercise g_e=new Generate_exercise();//生成题目类
        Output_result o_r;//输出结果到exercise.txt和answer.txt类
        Check_result c_r;//检查答案并输出到grade.txt类
        TreeNode tree;//公式树
        int exercise_num=100;//生成题目个数
        int limit_num=20;//界定生成数范围
        String exercise;//一个题目
        List<String> exercises=new ArrayList<>();//题目查重列表
        String answer;//一个答案
        String exercise_path;//题目路径
        String answer_path;//答案路径

        List<String> argsList=new ArrayList<>();
        Collections.addAll(argsList,args);//命令行参数列表
        String n="-n";
        String r="-r";
        String e="-e";
        String a="-a";


        //命令行输入生成题目个数n
        //输入界定生成数范围r
        if(argsList.contains(n)&&argsList.contains(r))//如果命令行参数包含"-n"与"-r"，则进行以下操作
        {
            if(Pattern.compile("^\\d{1,5}$").matcher(args[argsList.indexOf(n)+1]).matches())//判断“-n"后面是否是不超过10000的整数
            {
                exercise_num=Integer.parseInt(args[argsList.indexOf(n)+1]);
            }
            else
            {
                System.out.println("输入了不合法的参数，错误参数 -n");
                return;
            }
            if(Pattern.compile("^\\d{1,4}$").matcher(args[argsList.indexOf(r)+1]).matches())//判断“-r"后面是否是不超过1000的整数
            {
                limit_num=Integer.parseInt(args[argsList.indexOf(r)+1]);
            }
            else
            {
                System.out.println("输入了不合法的参数，错误参数 -r");
                return;
            }
            o_r=new Output_result("src\\resourse\\exercise.txt","src\\resourse\\answer.txt");//输出到这些文件里
            o_r.clearInfoForFile("src\\resourse\\exercise.txt");//生成题目前先清空题目文件和答案文件
            o_r.clearInfoForFile("src\\resourse\\answer.txt");

            //进行n次以下操作
            for(int i=1;i<=exercise_num;i++)//i小于“题目个数”
            {
                exercise=g_e.generate(limit_num);//生成一道题目
                String[] exc=TreeNode.toStringArrayTrimOutParrenthes(exercise);//去掉题目字符串外层括号，并根据空格把字符串划分为为字符串数组
                tree=new TreeNode(exc);//用题目字符串数组建一颗公式树
                Num resultNum=tree.calculate();//计算出公式树的结果，返回一个Num类
                Operate.simplify(resultNum);//化简Num类的分数
                if(Math.abs(resultNum.numer/ resultNum.denomin)>1000||resultNum.numer<0||resultNum.denomin<0)//生成的答案如果绝对值超过1000或者是负数，则丢弃
                {
                    i--;
                    continue;//扔掉该回答
                }
                if(Answer_result.IsRepeat(exercises,tree))//如果公式树和查重表达式列表里的某个式子重复
                {
                    i--;
                    continue;//扔掉该回答
                }
                else//否则加入该题目到查重表达式列表
                {
                    String dup=Answer_result.toPostifixExp(tree);//把公式树转化为后缀表达式
                    dup=Answer_result.getDupExpression(dup);//把后缀表达式转化为查重表达式
                    exercises.add(dup);//加入查重表达式列表
                }
                answer=Operate.fraction(resultNum);//将答案转化为一个字符串
                System.out.println("第"+i+"道题："+exercise+"\r\n+答案："+answer);
                o_r.output(exercise,answer,i);//输出该题目和答案字符串到文件
            }
            System.out.println("已将题目和答案分别输出到该程序目录下的\r\n"+"src\\resourse\\exercise.txt\r\n"+"src\\resourse\\answer.txt");
        }

        //如果命令行参数包含"-e"与"-a"，进行以下操作
        //判断答案是否正确，输出分数到grade.txt
        //输入的路径必须是绝对路径，不能是相对路径
        if(argsList.contains(e)&&argsList.contains(a)) {
            if(Pattern.compile("^(?<path>(?:[a-zA-Z]:)?\\\\(?:[^\\\\\\?\\/\\*\\|<>:\"]+\\\\)+)(?<filename>(?<name>[^\\\\\\?\\/\\*\\|<>:\"]+?)\\.(?<ext>txt))$").matcher(args[argsList.indexOf(n)+1]).matches())//判断“-e"后面是否是一个txt文件路径
            {
                exercise_path=args[argsList.indexOf(e)+1];
            }
            else
            {
                System.out.println("输入了不合法的文件路径，路径必须是绝对路径，且是txt文件，错误参数 -e");
                return;
            }
            if(Pattern.compile("^(?<path>(?:[a-zA-Z]:)?\\\\(?:[^\\\\\\?\\/\\*\\|<>:\"]+\\\\)+)(?<filename>(?<name>[^\\\\\\?\\/\\*\\|<>:\"]+?)\\.(?<ext>txt))$").matcher(args[argsList.indexOf(r)+1]).matches())//判断“-a"后面是否是一个txt文件路径
            {
                answer_path=args[argsList.indexOf(a)+1];
            }
            else
            {
                System.out.println("输入了不合法的文件路径，路径必须是绝对路径，且是txt文件，错误参数 -a");
                return;
            }
            c_r=new Check_result(exercise_path,answer_path);
            c_r.check();
        }
    }
}
