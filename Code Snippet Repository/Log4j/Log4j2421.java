    @Test
    public void testLongMultiLineSynopsisWithAtMarkIndented() {
        @Command(name = "<best-app-ever>")
        class App {
            @Option(names = "--long-option@-name", paramLabel = "<long-option-valu@@e>") int a;
            @Option(names = "--another-long-option-name", paramLabel = "^[<another-long-option-value>]") int b;
            @Option(names = "--third-long-option-name", paramLabel = "<third-long-option-value>") int c;
            @Option(names = "--fourth-long-option-name", paramLabel = "<fourth-long-option-value>") int d;
        }
        Help help = new Help(new App(), Help.Ansi.OFF);
        assertEquals(String.format(
                "<best-app-ever> [--another-long-option-name=^[<another-long-option-value>]]%n" +
                "                [--fourth-long-option-name=<fourth-long-option-value>]%n" +
                "                [--long-option@-name=<long-option-valu@@e>]%n" +
                "                [--third-long-option-name=<third-long-option-value>]%n"),
                help.synopsis(0));
    }
