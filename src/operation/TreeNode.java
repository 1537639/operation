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

            exc=TreeNode.toStringArrayTrimOutParrenthes(exc);
            if (exc == null) {
                return;
            }

            int length = exc.length;
            //int length=exc.length;
            int index = 0;
            if (hasOperation(exc)) {
                boolean lock=false;
                int parenthes = 0;
                for (int i = length - 1; i >= 0; i--) {
                    if (exc[i].equals("(")) {
                        parenthes--;
                    } else if (exc[i].equals(")")) {
                        parenthes++;
                    }
                    if (parenthes > 0) {
                        continue;
                    }
                    if (exc[i].equals("*") || exc[i].equals("/")&&lock==false) {
                        index = i;
                        if(exc[i].equals("/")&&lock==false)
                            lock=true;
                    } else if (exc[i].equals("+") || exc[i].equals("-")) {
                        index = i;
                        break;
                    }
                }
                if (isOperation(exc[index])) {
                    symbol = exc[index];
                    isSymbol=true;
                }

                String[] sbleft = new String[index];
                String[] sbright = new String[length-index-1];

                for (int i = 0; i < index; i++) {
                    sbleft[i]=exc[i];
                }
                for (int i = index + 1; i < length; i++) {
                    sbright[i-index-1] = exc[i];
                }
                left = new TreeNode(sbleft);
                right = new TreeNode(sbright);
            }else {
               // StringBuilder value = new StringBuilder();
                //value.append(exc);
                // String builder value to Num data
              //  String valueString= (value.toString());
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
            Num result;
            if (left == null && right == null) {
                result = data;
            } else {
                Num leftResult = left.calculate();
                Num rightResult = right.calculate();
                if(symbol.equals("+"))
                {
                    result =Operate.plus(leftResult,rightResult);
                }
                else if(symbol.equals("-"))
                {
                    result =Operate.subtract(leftResult,rightResult);
                }
                else if(symbol.equals("*"))
                {
                    result =Operate.multiple(leftResult,rightResult);
                }
                else if(symbol.equals("/"))
                {
                    result =Operate.divide(leftResult,rightResult);
                }
                else
                    return null;
            }
            return result;
        }


}
