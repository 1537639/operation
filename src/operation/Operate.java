package operation;


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
        simplify(number);
        return(number);
    }

    public static void simplify(Num number)//化简函数
    {
        int a=number.denomin;
        int b=number.numer;
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

    public static String fraction(Num number)//输出一个分数
    {
        if(number.denomin==1)
        {
            return String.valueOf(number.numer);
        }
        else if(number.denomin< number.numer)
        {
            int interger;
            int frac_numer;
            interger=number.numer/number.denomin;
            frac_numer=number.numer%number.denomin;
            return (interger +"'"+ frac_numer +"/"+ number.denomin);
        }
        else{
            return (number.numer+"/"+ number.denomin);
        }
    }

    public static boolean IsRepeat(String[] exercises,String exercise)//是否重复
    {
        for(int i=0;i< exercises.length;i++)
        {
            if(exercise.equals(exercises[i]))
                return true;
        }
        return false;
    }
}
