

class Problem {
    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            System.out.println("Invalid number of arguments.");
            return;
        }

        for (int i = 0; i < args.length; i += 2) {
            String parameter = args[i];
            String value = args[i + 1];
            System.out.println(parameter + "=" + value);
        }
    }
}