    static void generate(final String[] args, final Type type, final PrintStream printStream) {
        if (!validate(args)) {
            usage(printStream, type.generator());
            System.exit(-1);
        }
        final List<String> values = new ArrayList<>(Arrays.asList(args));
        final String classFQN = values.remove(0);
        final List<LevelInfo> levels = LevelInfo.parse(values, type.generator());
        printStream.println(generateSource(classFQN, levels, type));
    }
