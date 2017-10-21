    @Test
    public void testSynopsis_optionalOption_withSpaceSeparator() {
        @CommandLine.Command(separator = " ") class App {
            @Option(names = {"--verbose", "-v"}) boolean verbose;
            @Option(names = {"--count", "-c"}) int count;
            @Option(names = {"--help", "-h"}, hidden = true) boolean helpRequested;
        }
        Help help = new Help(new App(), Help.Ansi.OFF);
        assertEquals("<main class> [-v] [-c <count>]" + LINESEP, help.synopsis(0));
    }
