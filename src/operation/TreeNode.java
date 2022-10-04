package operation;

public class TreeNode{
    TreeNode left;
    TreeNode right;
    // 数据域
    Num data;  //String型符号节点
    String symbol;//运算符
    boolean isSymbol;
	  /*  public TreeNode(String symbol){
			this.symbol=symbol;
		}
	    public TreeNode(Num value){
			this.value=value;
		}
		*/
        public TreeNode(String[] exc)
        {

            exc=TreeNode.toStringArrayTrimOutParrenthes(exc);//对最外层括号进行去除，以找到最左运算符
            if (exc == null) {
                return;
            }//空值情况

            int length = exc.length;//去括号后的中缀表达式字符串
            int index = 0;//分割标志位
            if (hasOperation(exc)) {//此式中含有运算符
                boolean lock=false;//定义优先级锁且置为错误
                int parenthes = 0;//括号对数
                for (int i = length - 1; i >= 0; i--) {//从右向左推进查找
                    if (exc[i].equals("(")) {
                        parenthes--;//匹配左括号
                    } else if (exc[i].equals(")")) {
                        parenthes++;//匹配右括号
                    }
                    if (parenthes > 0) {
                        continue;//右括号仍然未匹配成对
                    }
                    if (exc[i].equals("*") || exc[i].equals("/")&&lock==false) {
                        index = i;//当匹配*或者/时，且优先级锁为false，分割标志位为当前位
                        if(exc[i].equals("/")&&lock==false)//当匹配的是除号且优先级锁为false，将优先级锁上锁
                            lock=true;//
                    } else if (exc[i].equals("+") || exc[i].equals("-")) {
                        index = i; //一旦匹配+或者-时，立即结束for语句
                        break;
                    }
                }
                if (isOperation(exc[index])) {
                    symbol = exc[index];  //当标志位的字符是运算符而不是括号时，置Num类的运算符类型为当前位的符号
                    isSymbol=true;//本位置是运算符
                }

                String[] sbleft = new String[index];//定义 构建分割位左边的运算式
                String[] sbright = new String[length-index-1];//定义 构建分割位右边的运算式

                for (int i = 0; i < index; i++) {
                    sbleft[i]=exc[i];
                }
                for (int i = index + 1; i < length; i++) {
                    sbright[i-index-1] = exc[i];
                }
                //上述两个循环以分割位为原点，继续建立左运算式和右运算式
                left = new TreeNode(sbleft);
                right = new TreeNode(sbright);
                //建立左子树和右子树
            }else {//此式中不含有运算符，直接赋值，且本位置是数字
                int data2=Integer.parseInt(exc[0]);
                data=new Num(data2,1);
                isSymbol=false;
            }
        }

        //字符串中有无运算符，有则返回true OK
        public boolean hasOperation(String[] cArray) {
            for (int i = 0; i < cArray.length; i++) {
                if (isOperation(cArray[i])) {
                    return true;
                }

            }
            return false;
        }
        //字符是否为运算符 OK
        public boolean isOperation(String c) {
            return (c.equals("+")|| c.equals("-") || c.equals("*")|| c.equals("/"));

        }
        //去除运算式的最外层括号
        public static String[] toStringArrayTrimOutParrenthes(String s) {
            String[] a=s.split("\\s+");
            int length=a.length;
            String[] b;
            int i;


            if(a[0].equals("(")&&a[length-1].equals(")"))
            {
                int par=0;
                for(i=0;i<length;i++)
                {
                    if(a[i].equals("("))
                        par++;
                    else if(a[i].equals(")"))
                        par--;

                    if(par==0)
                        break;
                }
                if(i!=length-1)
                {
                    b=new String[length];
                    b=a;
                    return b;
                }
                    b = new String[length - 2];
                    for (i = 1; i < length - 1; i++) {

                        b[i - 1] = a[i];
                    }

            }
            else {
                b=new String[length];
                b=a;
            }

            return b;
        }

    public static String[] toStringArrayTrimOutParrenthes(String[] a) {
        int length=a.length;
        String[] b;
        int i;

        if(a[0].equals("(")&&a[length-1].equals(")"))
        {
            int par=0;
            for(i=0;i<length;i++)
            {
                if(a[i].equals("("))
                    par++;
                else if(a[i].equals(")"))
                    par--;

                if(par==0)
                    break;
            }
            if(i!=length-1)
            {
                b=new String[length];
                b=a;
                return b;
            }
            b=new String[length-2];
            for(i=1;i<length-1;i++)
            {

                b[i-1]=a[i];
            }
        }
        else {
            b=new String[length];
            b=a;
        }

        return b;
    }
        //对应运算符进行计算
        public Num calculate() {
            Num result;//定义需要返回的答案
            if (left == null && right == null) {
                result = data;//当左子树为空且右子树为空，根节点聚集的答案为TreeNode节点的数字
            } else {//左子树不为空或右子树不为空
                Num leftResult = left.calculate();//左子树继续递归，计算左子树的结果
                Num rightResult = right.calculate();//右子树继续递归，计算右子树的结果
                if(symbol.equals("+"))
                {
                    result =Operate.plus(leftResult,rightResult);//调用加法函数
                }
                else if(symbol.equals("-"))
                {
                    result =Operate.subtract(leftResult,rightResult);//调用减法函数
                }
                else if(symbol.equals("*"))
                {
                    result =Operate.multiple(leftResult,rightResult);//调用乘法函数
                }
                else if(symbol.equals("/"))
                {
                    result =Operate.divide(leftResult,rightResult);//调用除法函数
                }
                else
                    return null;
            }
            return result;//每次递归一次函数就返回一个根节点聚集的答案
        }


}
