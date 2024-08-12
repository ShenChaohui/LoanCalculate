import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoanCalculate {
    private static final DecimalFormat df = new DecimalFormat("#0.00"); // 创建DecimalFormat对象，保留两位小数

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double loanAmount = 0;
        int months = 0;
        double annualRate = 0;

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

        // 获取并验证年利率
        while (true) {
            try {
                System.out.print("请输入年利率: ");
                annualRate = scanner.nextDouble();
                if (annualRate >= 0) {
                    break;
                }
                System.err.println("年利率必须大于等于0，请重新输入!");
            } catch (InputMismatchException e) {
                System.err.println("输入的不是有效的数字，请重新输入!");
                scanner.next(); // 清除错误的输入
            }
        }

        // 关闭scanner
        scanner.close();
        calculateEqualPrincipalAndInterest(loanAmount, months, annualRate);
        calculateEqualPrincipal(loanAmount, months, annualRate);
        calculateInterestBeforePrincipal(loanAmount, months, annualRate);
    }

    /**
     * 计算等额本息还款
     * 月利率 = 年利率/12/100
     * 月还款额 = 贷款金额 * 月利率 * (1 + 月利率)^n / ((1 + 月利率)^n - 1)
     * 
     * @param loanAmount 总贷款
     * @param months     贷款月数
     * @param annualRate 年利率
     */
    public static void calculateEqualPrincipalAndInterest(double loanAmount, int months, double annualRate) {
        // 计算月利率
        double monthlyRate = annualRate / 12 / 100;

        double totalPayment = 0;
        // 计算月还款额
        double monthlyPayment = loanAmount * monthlyRate * Math.pow((1 + monthlyRate), months)
                / (Math.pow((1 + monthlyRate), months) - 1);
        for (int i = 0; i < months; i++) {
            totalPayment += monthlyPayment;
        }
        // 输出等额本息还款相关信息，并保留两位小数
        System.err.println("--------------------------------------------");
        System.err.println("等额本息还款");
        System.err.println("总还款额：" + df.format(totalPayment));
        System.err.println("总利息：" + df.format(totalPayment - loanAmount));
        System.err.println("月还款额：" + df.format(monthlyPayment));
        System.err.println("--------------------------------------------");
    }

    /**
     * 等额本本金还款
     * 月利率 = 年利率/12/100
     * 每月还款额 = 剩余贷款金额 * 月利率 + 贷款金额 / 贷款月数
     * 
     * @param loanAmount
     * @param months
     * @param annualRate
     * @return
     */

    public static void calculateEqualPrincipal(double loanAmount, int months, double annualRate) {
        // 计算月利率
        double monthlyRate = annualRate / 12 / 100;
        // 初始化总还款额为0
        double totalPayment = 0;
        // 剩余本金初始化为贷款金额
        double remainingPrincipal = loanAmount;
        // 创建一个数组存储每个月的还款额
        double[] monthlyPayments = new double[months];

        // 计算每个月的还款额
        for (int i = 0; i < months; i++) {
            // 当月利息
            double monthlyInterest = remainingPrincipal * monthlyRate;
            // 当月还款额（利息 + 每月应还本金）
            double monthlyPayment = monthlyInterest + (loanAmount / months);
            // 存储当月还款额
            monthlyPayments[i] = monthlyPayment;
            // 累加总还款额
            totalPayment += monthlyPayment;
            // 更新剩余本金
            remainingPrincipal -= loanAmount / months;

        }

        // 输出等额本金还款相关信息，并保留两位小数
        System.err.println("--------------------------------------------");
        System.err.println("等额本金还款");
        System.err.println("总还款额：" + df.format(totalPayment));
        System.err.println("总利息：" + df.format(totalPayment - loanAmount));
        System.err.println("首月还款额：" + df.format(monthlyPayments[0]));
        System.err.println("末月还款额：" + df.format(monthlyPayments[months - 1]));
        System.err.println("每月递减：" + df.format(monthlyPayments[0] - monthlyPayments[1]));
        System.err.println("--------------------------------------------");

    }

    /**
     * 先息后本
     * 
     * @param loanAmount
     * @param months
     * @param annualRate
     */
    private static void calculateInterestBeforePrincipal(double loanAmount, int months, double annualRate) {
        // 计算月利率
        double monthlyRate = annualRate / 12 / 100;
        double monthlyInterest = loanAmount * monthlyRate;
        double totalPayment = monthlyInterest * months + loanAmount;
        // 输出等额本金还款相关信息，并保留两位小数
        System.err.println("--------------------------------------------");
        System.err.println("先息后本还款");
        System.err.println("总还款额：" + df.format(totalPayment));
        System.err.println("总利息：" + df.format(totalPayment - loanAmount));
        System.err.println("每月还款额：" + df.format(monthlyInterest));
        System.err.println("末月还款" + df.format(loanAmount+monthlyInterest));
        System.err.println("--------------------------------------------");
    }
}
