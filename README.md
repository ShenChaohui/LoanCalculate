# LoanCalculate
贷款计算和利率反推算法

## 贷款计算
```
PS D:\JavaProjects\LoanCalculate>  d:; cd 'd:\JavaProjects\LoanCalculate'; & 'C:\Program Files\Java\jdk-17\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'D:\JavaProjects\LoanCalculate\bin' 'LoanCalculate'    
输入贷款金额: 10000
请输入贷款月数: 12
请输入年利率: 3
--------------------------------------------
等额本息还款
总还款额：10163.24
总利息：163.24
月还款额：846.94
--------------------------------------------
--------------------------------------------
等额本金还款
总还款额：10162.50
总利息：162.50
首月还款额：858.33
末月还款额：835.42
每月递减：2.08
--------------------------------------------
--------------------------------------------
先息后本还款
总还款额：10300.00
总利息：300.00
每月还款额：25.00
末月还款10025.00
--------------------------------------------
```

## 贷款利率反推
### 等额本息反推
```
PS D:\JavaProjects\LoanCalculate>  d:; cd 'd:\JavaProjects\LoanCalculate'; & 'C:\Program Files\Java\jdk-17\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'D:\JavaProjects\LoanCalculate\bin' 'InterestRateReversal'
输入贷款金额: 10000
请输入贷款月数: 12
请输入每月还款额: 846.94
等额本息方式计算得出的年利率为：3.00
等额本金计算得出的年利率为：1.63
PS D:\JavaProjects\LoanCalculate>
```

### 等额本金反推
```
PS D:\JavaProjects\LoanCalculate>  d:; cd 'd:\JavaProjects\LoanCalculate'; & 'C:\Program Files\Java\jdk-17\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'D:\JavaProjects\LoanCalculate\bin' 'InterestRateReversal'
输入贷款金额: 10000
请输入贷款月数: 12
请输入每月还款额: 858.33
等额本息方式计算得出的年利率为：5.49
等额本金计算得出的年利率为：3.00
PS D:\JavaProjects\LoanCalculate> 
```
