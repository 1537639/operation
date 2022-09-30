package operation;

import java.util.Random;

public class Generate_exercise {
    public String generate(int num)//输入一个界定数范围，生成一个题目，返回一个题目字符串
    {
        Random random=new Random();
        int[] number=new int[4];//储存数字数组
        char[] operator=new char[3];//储存操作符数组
        int operator_num=0;//该题目中含有的操作符数
        int left_par=0;//左括号数
        int right_par=0;//右括号数
        StringBuilder exercise=new StringBuilder();//构造题目字符串
        operator_num=random.nextInt(3)+1;//随机生成一个操作符数
        left_par=random.nextInt(operator_num+1);
        right_par=left_par;

        int i;
        for (i = 0; i < operator_num; i++) {//随机生成操作符，与数字
            int e=0;
            while(e==0)
            {
                e= random.nextInt(num);
            }
            number[i]=e;

            int a;
            a= random.nextInt(4);
            //a=3;
            switch(a)
            {
                case 0 :operator[i]='+';break;
                case 1 :operator[i]='-';break;
                case 2 :operator[i]='*';break;
                case 3 :operator[i]='/';break;
               // case 4 :operator[i]='/';break;
            }
        }
        int e=0;
        e= random.nextInt(num+1);
        number[i]=e;

        int j;//遍历输出
        boolean lock=false;//若刚输出左括号，对右括号输出上锁，直到再输出一个操作符才能输出右括号，是为了避免出现( 1 ) + 2这种情况
        if(random.nextInt(10)>5&&left_par!=0)//输出左括号
        {
            exercise.append("( ");
            left_par--;
            lock=true;
        }
        for(j = 0 ; j < operator_num ; j++){
            exercise.append(number[j]);
            exercise.append(" ");
            if(random.nextInt(10)<=5&&left_par<right_par&&lock==false)//剩余右括号数必须大于左括号数且没有“刚输出一个左括号”时才能输出右括号
            {
                exercise.append(") ");
                right_par--;
            }
            exercise.append(operator[j]);
            exercise.append(' ');
            lock=false;
            if(random.nextInt(10)>5&&left_par!=0&&j+1!=operator_num)//剩余左括号不为0且还没有达到式子末尾时可以输出左括号
            {
                exercise.append("( ");
                left_par--;
                lock=true;
            }
        }//生成题目字符串
        exercise.append(number[j]);
        while(right_par>left_par)
        {
            exercise.append(" )");
            right_par--;
        }
        String exercise_str=exercise.toString();
        return exercise_str;
    }
}