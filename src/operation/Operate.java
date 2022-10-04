package operation;


import java.util.List;

public class Operate {
    public static Num plus(Num num_1, Num num_2)//加法函数
    {
        int denomin;
        int numer;
        if (num_1.denomin==num_2.denomin)
        {
            numer=num_1.numer+num_2.numer;
            denomin=num_1.denomin;
            Num number=new Num(numer,denomin);
            simplify(number);
            return number;
        }
        else
        {
            int a=num_1.denomin;
            int b=num_2.denomin;
            int c=num_1.denomin%num_2.denomin;
            int d=num_1.denomin*num_2.denomin;
            while(c!=0)
            {
                a=b;
                b=c;
                c=a%b;
            }
            Num number=new Num(num_1.numer*(d/b/num_1.denomin)+num_2.numer*(d/b/num_2.denomin),d/b);
            simplify(number);
            return number;
        }
    }
    public static Num subtract(Num num_1, Num num_2)//减法函数
    {
        int denomin;
        int numer;

        if (num_1.denomin==num_2.denomin)
        {
            numer=num_1.numer-num_2.numer;
            denomin=num_1.denomin;
            Num number=new Num(numer,denomin);
            simplify(number);
            return number;
        }
        else
        {
            int a=num_1.denomin;
            int b=num_2.denomin;
            int c=num_1.denomin%num_2.denomin;
            int d=num_1.denomin*num_2.denomin;
            while(c!=0)
            {
                a=b;
                b=c;
                c=a%b;
            }
            Num number=new Num(num_1.numer*(d/b/num_1.denomin)-num_2.numer*(d/b/num_2.denomin),d/b);
            simplify(number);
            return number;
        }
    }
    public static Num multiple(Num num_1, Num num_2)//乘法函数
    {
        Num number=new Num(num_1.numer* num_2.numer,num_1.denomin* num_2.denomin);
        simplify(number);
        return(number);
    }
    public static Num divide(Num num_1, Num num_2)//除法函数
    {
        Num number=new Num(num_1.numer* num_2.denomin,num_1.denomin* num_2.numer);
        if(number.denomin==0)
        {
            number.numer=1000000;
            number.denomin=1;
        }
        simplify(number);
        return(number);
    }

    public static void simplify(Num number)//化简函数
    {
        int a=number.denomin;
        int b=number.numer;
        if(b==0)
        {
            number.numer=0;
            number.denomin=1;
            return;
        }
        int c=a%b;
        while(c!=0)
        {
            a=b;
            b=c;
            c=a%b;
        }
        number.numer/=b;
        number.denomin/=b;
    }

    public static String fraction(Num number)//输出一个分数字符串
    {
        if(number.denomin==1)
        {
            if(number.numer<0)
                return null;
            return String.valueOf(number.numer);
        }
        else if(number.denomin< number.numer)
        {
            int interger;
            int frac_numer;
            interger=number.numer/number.denomin;
            frac_numer=number.numer%number.denomin;
            if(interger<0||frac_numer<0)
                return null;
            return (interger +"'"+ frac_numer +"/"+ number.denomin);
        }
        else{
            return (number.numer+"/"+ number.denomin);
        }
    }


}
