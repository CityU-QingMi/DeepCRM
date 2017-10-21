        public static void main(String[] args) throws IOException {
            if (args.length == 2) {
                install(new File(args[0]), new File(args[1]));
            } else if (args.length == 3) {
                install(
                    new File(args[0]),
                    new File(args[1]),
                    new File(args[2]));
            } else {
                System.err.println(
                    "Usage: <command> <input class file> "
                        + "<attribute file> <output class file name>\n"
                        + "<command> <input/output class file> <attribute file>");
            }
        }
