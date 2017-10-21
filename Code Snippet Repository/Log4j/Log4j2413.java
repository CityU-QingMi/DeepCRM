    @Test
    public void testSynopsis_withSeparator_withLabeledRequiredParameters_ANSI() {
        @CommandLine.Command(separator = "=") class App {
            @Option(names = {"--verbose", "-v"}) boolean verbose;
            @Option(names = {"--count", "-c"}) int count;
            @Option(names = {"--help", "-h"}, hidden = true) boolean helpRequested;
            @Parameters(paramLabel = "FILE", arity = "1..*") File[] files;
        }
        Help help = new Help(new App(), Help.Ansi.ON);
        assertEquals(Help.Ansi.ON.new Text("@|bold <main class>|@ [@|yellow -v|@] [@|yellow -c|@=@|italic <count>|@] @|yellow FILE|@ [@|yellow FILE|@...]" + LINESEP),
                help.synopsis(0));
    }
