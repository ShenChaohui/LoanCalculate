import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterestRateReversal {
    private static final DecimalFormat df = new DecimalFormat("#0.00"); // 创建DecimalFormat对象，保留两位小数

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double loanAmount = 0;
        int months = 0;
        double monthlyPayment = 0;

        // 获取并验证贷款金额
        while (true) {
            try {
                System.out.print("输入贷款金额: ");
                loanAmount = scanner.nextDouble();
                if (loanAmount > 0) {
                    break;
                }
                System.err.println("贷款金额必须大于0，请重新输入!");
            } catch (InputMismatchException e) {
                System.err.println("输入的不是有效的数字，请重新输入!");
                scanner.next(); // 清除错误的输入
            }
        }

        // 获取并验证贷款月数
        while (true) {
            try {
                System.out.print("请输入贷款月数: ");
                months = scanner.nextInt();
                if (months >= 2) {
                    break;
                }
                System.err.println("贷款月数必须大于等于2，请重新输入!");
            } catch (InputMismatchException e) {
                System.err.println("输入的不是有效的数字，请重新输入!");
                scanner.next(); // 清除错误的输入
            }
        }

        // 获取并验证每月还款额
        while (true) {
            try {
                System.out.print("请输入每月还款额: ");
                monthlyPayment = scanner.nextDouble();
                if (monthlyPayment > 0) {
                    break;
                }
                System.err.println("每月还款额必须大于0，请重新输入!");
            } catch (InputMismatchException e) {
                System.err.println("输入的不是有效的数字，请重新输入!");
                scanner.next(); // 清除错误的输入
            }
        }

        // 关闭scanner
        scanner.close();

        // 反推年利率
        System.err.println("等额本息方式计算得出的年利率为："
                + df.format(calculateAnnualRateFromMonthlyPaymentEqualPrincipalAndInterest(loanAmount, months, monthlyPayment)));
        System.err.println("等额本金计算得出的年利率为："
                + df.format(
                        calculateAnnualRateFromFirstMonthPaymentEqualPrincipal(loanAmount, months, monthlyPayment)));

    }

    /**
     * 等额本息
     * 
     * @param loanAmount     总贷款
     * @param months         贷款月数
     * @param monthlyPayment 每月还款额
     * @return 年利率
     */
    private static double calculateAnnualRateFromMonthlyPaymentEqualPrincipalAndInterest(double loanAmount, int months, double monthlyPayment) {
        double low = 0.0001; // 初始最小利率
        double high = 100; // 初始最大利率
        double rate = (low + high) / 2; // 初始猜测利率
        double epsilon = 1e-6; // 容差值
        double calculatedMonthlyPayment = 0;

        while (true) {
            double monthlyRate = rate / 12 / 100;
            calculatedMonthlyPayment = loanAmount * monthlyRate * Math.pow((1 + monthlyRate), months)
                    / (Math.pow((1 + monthlyRate), months) - 1);

            if (Math.abs(calculatedMonthlyPayment - monthlyPayment) < epsilon) {
                return rate;
            }

            if (calculatedMonthlyPayment > monthlyPayment) {
                high = rate;
            } else {
                low = rate;
            }

            rate = (low + high) / 2;
        }
    }

    /**
     * 根据贷款金额、贷款月数和首月还款额反推年利率（等额本金还款）
     * 
     * @param loanAmount        总贷款
     * @param months            贷款月数
     * @param firstMonthPayment 首月还款额
     * @return 年利率
     */
    private static double calculateAnnualRateFromFirstMonthPaymentEqualPrincipal(double loanAmount, int months,
            double firstMonthPayment) {
        double low = 0.0001; // 初始最小利率
        double high = 100; // 初始最大利率
        double rate = (low + high) / 2; // 初始猜测利率
        double epsilon = 1e-6; // 容差值
        double calculatedFirstMonthPayment = 0;
        double monthlyPrincipal = loanAmount / months; // 每月偿还的本金

        while (true) {
            double monthlyRate = rate / 12 / 100;
            double monthlyInterest = loanAmount * monthlyRate; // 首月的利息
            calculatedFirstMonthPayment = monthlyInterest + monthlyPrincipal;

            if (Math.abs(calculatedFirstMonthPayment - firstMonthPayment) < epsilon) {
                return rate;
            }

            if (calculatedFirstMonthPayment > firstMonthPayment) {
                high = rate;
            } else {
                low = rate;
            }

            rate = (low + high) / 2;
        }
    }
}
