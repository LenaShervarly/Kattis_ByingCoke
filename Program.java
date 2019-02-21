import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    private static final int COKE_PRICE = 8;
	
	public static void main(String args[]) {

        try (Scanner scanner = new Scanner(System.in)) {
            int numberOfTestCases = Integer.valueOf(scanner.nextLine());
            ArrayList<Integer> results = new ArrayList<>(); 
            
            for (int i = 0; i < numberOfTestCases; i++) {
                int[] testCase = Arrays.stream(scanner.nextLine()
                		.split(" "))
                		.mapToInt(t -> Integer.valueOf(t))
                		.toArray();
                
                int initialCokesToBuy = testCase[0];
                int totalOneCoins = testCase[1];
                int totalFiveCoins = testCase[2];
                int totalTenCoins = testCase[3];
                
                results.add(getLeastNumberOfCoins(initialCokesToBuy, totalOneCoins, totalFiveCoins, totalTenCoins));
            }
            for(int caseResult : results) {
            	System.out.println(caseResult);
            } 
            
        } catch (Exception e) {
        	System.out.println("Something went wrong. Please check that you provide test valuus as followwing \"n n n n\"");
        }
    }

    private static int getLeastNumberOfCoins(int initialCokesToBuy, int totalOneCoins, int totalFiveCoins, int totalTenCoins) {
    	
        int result = 0;
        int cokesLeftToBuy = initialCokesToBuy;

        if (cokesLeftToBuy <= totalTenCoins) {
            return cokesLeftToBuy;
        } else {        	
            result += totalTenCoins;
            cokesLeftToBuy -= totalTenCoins;
            
            if (totalFiveCoins >= cokesLeftToBuy) {
            	int remainingFiveCoinsAfterUsingOnePerCoke = totalFiveCoins - cokesLeftToBuy;
            	
            	if (remainingFiveCoinsAfterUsingOnePerCoke >= cokesLeftToBuy) {                 		
            		return result + cokesLeftToBuy * 2;            	
            	
            	} else {           		
            		cokesLeftToBuy -= remainingFiveCoinsAfterUsingOnePerCoke;
            		return result + totalFiveCoins + cokesLeftToBuy * 3;
            	}            	
            } else {
            	int coinsPerCoke = 4;
            	int spentOnCokeWithFiveAndOneCoins = totalFiveCoins * coinsPerCoke;
            	
            	cokesLeftToBuy -= totalFiveCoins;
            	result += spentOnCokeWithFiveAndOneCoins + cokesLeftToBuy * COKE_PRICE;
        		
            	int alternativeresult = Integer.MAX_VALUE;
        		if(totalTenCoins >= 1 && totalOneCoins >= 3 ) {
        			int coinsSpentToGetFiveAsChange = 4; // if you'll first insert 3 one coins and then 10
            		
            		alternativeresult = coinsSpentToGetFiveAsChange 
            				+ getLeastNumberOfCoins(initialCokesToBuy -1, totalOneCoins -3, totalFiveCoins +1, totalTenCoins -1);	
        		}  
        		return Math.min(result, alternativeresult);
            }
        }
    }
}