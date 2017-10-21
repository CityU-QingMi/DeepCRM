    @Test
    public void testSynopsis_firstLineLengthAdjustedForSynopsisHeading() throws Exception {
        //Usage: small-test-program [-acorv!?] [--version] [-h <number>] [-p <file>|<folder>] [-d
//                 <folder> [<folder>]] [-i <includePattern>
//                 [<includePattern>...]]
        @CommandLine.Command(name="small-test-program", sortOptions = false, separator = " ")
        class App {
            @Option(names = "-a") boolean a;
            @Option(names = "-c") boolean c;
            @Option(names = "-o") boolean o;
            @Option(names = "-r") boolean r;
            @Option(names = "-v") boolean v;
            @Option(names = "-!") boolean exclamation;
            @Option(names = "-?") boolean question;
            @Option(names = {"--version"}) boolean version;
            @Option(names = {"--handle", "-h"}) int number;
            @Option(names = {"--ppp", "-p"}, paramLabel = "<file>|<folder>") File f;
            @Option(names = {"--ddd", "-d"}, paramLabel = "<folder>", arity="1..2") File[] d;
            @Option(names = {"--include", "-i"}, paramLabel = "<includePattern>") String pattern;
        }
        Help help = new Help(new App(), Help.Ansi.OFF);
        String expected = "" +
                "Usage: small-test-program [-!?acorv] [--version] [-h <number>] [-i" + LINESEP +
                "                          <includePattern>] [-p <file>|<folder>] [-d <folder>" + LINESEP +
                "                          [<folder>]]" + LINESEP;
        assertEquals(expected, help.synopsisHeading() + help.synopsis(help.synopsisHeadingLength()));

        help.synopsisHeading = "Usage:%n";
        expected = "" +
                "Usage:" + LINESEP +
                "small-test-program [-!?acorv] [--version] [-h <number>] [-i <includePattern>]" + LINESEP +
                "                   [-p <file>|<folder>] [-d <folder> [<folder>]]" + LINESEP;
        assertEquals(expected, help.synopsisHeading() + help.synopsis(help.synopsisHeadingLength()));
    }
