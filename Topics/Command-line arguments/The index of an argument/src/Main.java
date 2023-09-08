
class Problem {
    public static void main(String[] args) {
        int index = -1;

        for (int i = 0; i < args.length; i++) {
            if ("test".equals(args[i])) {
                index = i;
                break;
            }
        }

        System.out.println(index);
    }
}