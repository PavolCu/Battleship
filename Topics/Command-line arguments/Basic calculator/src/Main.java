

class Problem {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Please provide an operator and two integers.");
            return;
        }

        String operator = args[0];
        int num1;
        int num2;

        try {
            num1 = Integer.parseInt(args[1]);
            num2 = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please provide valid integers.");
            return;
        }

        if ("+".equals(operator)) {
            System.out.println(num1 + num2);
        } else if ("-".equals(operator)) {
            System.out.println(num1 - num2);
        } else if ("*".equals(operator)) {
            System.out.println(num1 * num2);
        } else {
            System.out.println("Unknown operator");
        }
    }
}
