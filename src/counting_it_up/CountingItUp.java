package counting_it_up;

/**
 * Class that does the "Counting it up" program
 */
public class CountingItUp {

    public static long nActual; 
    public static long kActual; 
    public static long nFact;
    public static long kFact;

    public static void main(String[] args){
        setup(10, 11);
    }

    private static long denominator(long kF, long n, long k){
        long result = (kF * (n - k)); 
        System.out.println(result);
        long fact = factorised(result); 
        return fact;
    }

    private static void setup(long n, long k){

    }
  
    private static long factorised(long n){
        long fact = 1; 
        for(int i =1; i<= n; i++){
            fact *= i; 
        }
        return fact;
    }

    
    

    private static long formula( long nF, long d){
        long result = nF / d;
        return result;
    }


}