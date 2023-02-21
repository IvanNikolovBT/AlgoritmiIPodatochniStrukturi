    package KOLOKVIUM;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Stack;

    public class ExpressionEvaluator {

        public static int evaluateExpression(String expression){
            Stack<Integer> broj=new Stack<>();
            Stack<String> znak=new Stack<>();
            for(int i=0;i< expression.length();i++)
            {
                int a=0;
                if(Character.isDigit(expression.charAt(i)))
                {
                    a=0;
                    while(i!=expression.length()&&Character.isDigit(expression.charAt(i)))
                    {
                        a=a*10+Integer.parseInt(String.valueOf(expression.charAt(i)));
                        i++;
                        if(i==expression.length()||!Character.isDigit(expression.charAt(i)))
                            break;

                    }

                    broj.push(a);
                }
                if(!znak.isEmpty()&&znak.peek().equals("*"))
                {
                    broj.push(broj.pop()*broj.pop());
                    znak.pop();

                }
                if(i!=expression.length()&&expression.charAt(i) == '+')
                {

                    if(!znak.isEmpty()&&znak.peek().equals("+"))
                        broj.push(broj.pop()+broj.pop());
                    else
                        znak.push("+");

                }
                else
                    znak.push("*");
            }
            return broj.pop()+broj.pop();
        }
        public static void main(String[] args) throws IOException {
            BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
            System.out.println(evaluateExpression(input.readLine()));
        }

    }