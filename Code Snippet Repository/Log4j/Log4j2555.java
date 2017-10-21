    private void showSimpleExampleUsage() {
        class Example {
            @Option(names = { "-v", "--verbose" }, description = "Be verbose.")
            private boolean verbose = false;

            @Option(names = { "-h", "--help" }, help = true,
                    description = "Displays this help message and quits.")
            private boolean helpRequested = false;

            @Parameters(arity = "1..*", paramLabel = "FILE", description = "File(s) to process.")
            private File[] inputFiles;
        }
        CommandLine.usage(new Example(), System.out);
    }
