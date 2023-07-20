public class CalcMain {

    public static void main(String[] args) {
        try {
            double result = Calc.run(args[0]);
            System.out.println("result= " + result);
            
        } catch (Exception e) {
          System.out.println("ERROR: " + e);
        }
        
    }
}
