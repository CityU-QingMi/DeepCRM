        List<CommandLine> parse(String... args) {
            Assert.notNull(args, "argument array");
            Stack<String> arguments = new Stack<String>();
            for (int i = args.length - 1; i >= 0; i--) {
                arguments.push(args[i]);
            }
            List<CommandLine> result = new ArrayList<CommandLine>();
            parse(result, arguments, args);
            return result;
        }
