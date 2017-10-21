    @Test
    public void testSynopsis_clustersRequiredBooleanOptionsSeparately_ANSI() {
        @CommandLine.Command(separator = "=") class App {
            @Option(names = {"--verbose", "-v"}) boolean verbose;
            @Option(names = {"--aaaa", "-a"}) boolean aBoolean;
            @Option(names = {"--xxxx", "-x"}) Boolean xBoolean;
            @Option(names = {"--Verbose", "-V"}, required = true) boolean requiredVerbose;
            @Option(names = {"--Aaaa", "-A"}, required = true) boolean requiredABoolean;
            @Option(names = {"--Xxxx", "-X"}, required = true) Boolean requiredXBoolean;
            @Option(names = {"--count", "-c"}, paramLabel = "COUNT") int count;
        }
        Help help = new Help(new App(), Help.defaultColorScheme(Help.Ansi.ON));
        assertEquals(Help.Ansi.ON.new Text("@|bold <main class>|@ @|yellow -AVX|@ [@|yellow -avx|@] [@|yellow -c|@=@|italic COUNT|@]" + LINESEP),
                help.synopsis(0));
    }
