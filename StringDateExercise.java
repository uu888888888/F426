import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;

/**
 * 环节6 课堂练习5：字符串处理与日期计算综合练习（交互版）
 * 每题先询问用户输入，再处理并输出结果
 */
public class StringDateExercise {

    static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== 课堂练习5：字符串处理与日期计算（交互版）=====");
        System.out.println();
        task1_reverse();
        task2_countChar();
        task3_orderNo();
        task4_countdown();
        task5_parseAndFormat();
        System.out.println("===== 全部题目完成 =====");
    }

    // 题1：反转字符串（StringBuilder.reverse）—— 编辑倒序校验
    static void task1_reverse() {
        System.out.println("【题1】反转字符串（编辑倒序校验）");
        System.out.print("请输入一个字符串: ");
        String input = SC.nextLine();
        String reversed = new StringBuilder(input).reverse().toString();
        System.out.println("反转结果: " + reversed);
        System.out.println();
    }

    // 题2：统计字符串中某字符出现次数 —— 日志关键字计数
    static void task2_countChar() {
        System.out.println("【题2】统计字符出现次数（日志关键字计数）");
        System.out.print("请输入一段日志文本: ");
        String text = SC.nextLine();
        System.out.print("请输入要统计的字符（1个）: ");
        String line = SC.nextLine().trim();
        char target = line.isEmpty() ? ' ' : line.charAt(0);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                count++;
            }
        }
        System.out.println("字符 '" + target + "' 出现次数: " + count);
        System.out.println();
    }

    // 题3：生成订单号 yyyyMMddHHmmss + "_" + 4位随机数 —— 订单流水号
    static void task3_orderNo() {
        System.out.println("【题3】生成订单号（订单流水号，随机部分每次运行不同）");
        System.out.print("是否开始生成订单号？(y/n): ");
        String answer = SC.nextLine().trim();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String orderNo = timestamp + "_" + String.format("%04d", new Random().nextInt(10000));
            System.out.println("订单号: " + orderNo);
        } else {
            System.out.println("已取消生成订单号");
        }
        System.out.println();
    }

    // 题4：计算今天距离目标日期还有多少天（ChronoUnit.DAYS）—— 活动倒计时
    static void task4_countdown() {
        System.out.println("【题4】活动倒计时（ChronoUnit.DAYS）");
        System.out.print("请输入目标日期(yyyy-MM-dd)，回车默认 2026-09-30: ");
        String line = SC.nextLine().trim();
        try {
            LocalDate target = line.isEmpty() ? LocalDate.of(2026, 9, 30) : LocalDate.parse(line);
            LocalDate today = LocalDate.now();
            long days = ChronoUnit.DAYS.between(today, target);
            System.out.println("今天: " + today + "，距离 " + target + " 还有 " + days + " 天");
        } catch (DateTimeParseException e) {
            System.out.println("日期格式错误，应为 yyyy-MM-dd");
        }
        System.out.println();
    }

    // 题5：把日期时间字符串解析后按「yyyy年M月d日」格式输出 —— 报表日期展示（parse + format）
    static void task5_parseAndFormat() {
        System.out.println("【题5】报表日期展示（parse + format）");
        System.out.print("请输入日期时间(yyyy-MM-dd HH:mm:ss)，回车默认 2026-08-27 14:30:00: ");
        String line = SC.nextLine().trim();
        String input = line.isEmpty() ? "2026-08-27 14:30:00" : line;

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, inputFormatter);
            System.out.println("原始字符串: " + input);
            System.out.println("报表格式: " + dateTime.format(outputFormatter));
        } catch (DateTimeParseException e) {
            System.out.println("格式错误，应为 yyyy-MM-dd HH:mm:ss");
        }
        System.out.println();
    }
}
