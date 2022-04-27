package counting_it_up;

/**
 * Class that does the "Counting it up" program
 */
public class CountingItUp {

 

    public static void main(String[] args){
        PositiveBigInt n = new PositiveBigInt("20");
        PositiveBigInt k = new PositiveBigInt("2");
        System.out.println(setup(n, k));
        
    }
    /**
     * this method creates the denomeinaitor of the forumal. 
     * 
     * @param kF takes k factorised 
     * @param n the value n 
     * @param k the value k
     * @return the denominator needed in the forumula 
     */
    private static PositiveBigInt denominator(PositiveBigInt kF, PositiveBigInt n, PositiveBigInt k){
        PositiveBigInt nplaceHold = n.diff(k);
        PositiveBigInt nPlaceHoldF = nplaceHold.factorial();
        PositiveBigInt result = kF.mul(nPlaceHoldF); 
      
        return result;
    }
    /**
     * this method calls the methods denominator and formula to calculate problem
     * @param n the value given
     * @param k the value given
     * @return the result of forumla 
     */
    private static PositiveBigInt setup(PositiveBigInt n, PositiveBigInt k){
        PositiveBigInt answer = formula(n.factorial(), denominator(k.factorial(), n, k));
     
        return answer;
    }
  
   
    
    
    /**
     * this method calculates the result of the numorator devided by the dinomiator.
     * @param nF this is n factorised
     * @param d the result of the denominator. 
     * @return the result of the numorator devided by the dinominator.
     */
    private static PositiveBigInt formula( PositiveBigInt nF, PositiveBigInt d){
        PositiveBigInt result = nF.div(d);
        
        return result;
    }


}