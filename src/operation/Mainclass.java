package operation;

public class Mainclass {
    public static void main(String[] args) {
        Generate_exercise g_e=new Generate_exercise();//生成题目类
        Answer_result a_r=new Answer_result();//回答问题类
        Output_result o_r=new Output_result();//输出结果到exercise.txt和answer.txt类
        Check_result c_r=new Check_result(a_r);//检查答案并输出到grade.txt类
        int exercise_num;//生成题目个数
        int limit_num;//界定生成数范围
        String exercise;//一个题目
        String answer;//一个答案


        //输入生成题目个数n

        //输入界定生成数范围

        //进行n次以下操作


        //判断答案是否正确，输出分数到grade.txt

    }
}
