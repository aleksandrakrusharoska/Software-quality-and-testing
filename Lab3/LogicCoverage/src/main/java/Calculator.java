public class Calculator {
    /**
     * Calculates the adjusted interest rate based on borrower and loan characteristics.
     *
     * @param creditScore Borrower's credit score
     * @param isFirstTimeBorrower Whether this is the borrower's first loan
     * @param loanAmount Loan amount in EUR
     * @param yearsEmployed Number of years employed
     * @return Adjusted interest rate (as a %)
     */
    public static double calculateAdjustedInterestRate(int creditScore,
                                                       boolean isFirstTimeBorrower,
                                                       double loanAmount,
                                                       int yearsEmployed) {
        if ((yearsEmployed > 3 && loanAmount<= 10000 && !isFirstTimeBorrower) || (loanAmount > 10000 && creditScore > 700)) {
            return 3.5 + (loanAmount / 10000);  // lower base + scale with amount
        } else {
            return 6.5 + (loanAmount / 5000);   // higher base + scale with amount
        }
    }
}
