    @Test
    public void testSynopsis_withSeparator_withLabeledRequiredParameters() {
        @CommandLine.Command(separator = "=") class App {
            @Option(names = {"--verbose", "-v"}) boolean verbose;
            @Option(names = {"--count", "-c"}) int count;
            @Option(names = {"--help", "-h"}, hidden = true) boolean helpRequested;
            @Parameters(paramLabel = "FILE", arity = "1..*") File[] files;
        }
        Help help = new Help(new App(), Help.Ansi.OFF);
        assertEquals("<main class> [-v] [-c=<count>] FILE [FILE...]" + LINESEP, help.synopsis(0));
    }
