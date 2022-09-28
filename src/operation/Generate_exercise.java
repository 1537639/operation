package operation;

import java.util.Random;

public class Generate_exercise {
    public String generate(int num)//输入一个界定数范围，生成一个题目，返回一个题目字符串
    {
        Random random=new Random();
        int[] number=new int[4];//储存数字数组
        char[] operator=new char[3];//储存操作符数组
        int operator_num=0;//该题目中含有的操作符数
        String exercise;//题目字符串
        while(operator_num==0)
        {
            operator_num=random.nextInt(3);//随机生成一个操作符数
        }

        int i;
        for (i = 0; i < operator_num; i++) {//随机生成操作符，与数字
            int e=0;
            while(e==0)
            {
                e= random.nextInt(num);
            }
            number[i]=e;

            int a;
            a= random.nextInt(3);
            switch(a)
            {
                case 0 :operator[i]='+';
                case 1 :operator[i]='-';
                case 2 :operator[i]='*';
                case 3 :operator[i]='/';
            }
        }
        int e=0;
        while(e==0)
        {
            e= random.nextInt(num);
        }
        number[i]=e;

        exercise=String.valueOf(number[0]);
        for(int j = 0 ; j < operator.length ; j++){
            exercise=exercise+' ';
            exercise=exercise+operator[j];
            exercise=exercise+' ';
            exercise=exercise+String.valueOf(number[j+1]);
        }//生成题目字符串
        return exercise;
    }
}
