    @Test
    public void testLongMultiLineSynopsisWithAtMarkIndented_ANSI() {
        @Command(name = "<best-app-ever>")
        class App {
            @Option(names = "--long-option@-name", paramLabel = "<long-option-valu@@e>") int a;
            @Option(names = "--another-long-option-name", paramLabel = "^[<another-long-option-value>]") int b;
            @Option(names = "--third-long-option-name", paramLabel = "<third-long-option-value>") int c;
            @Option(names = "--fourth-long-option-name", paramLabel = "<fourth-long-option-value>") int d;
        }
        Help help = new Help(new App(), Help.defaultColorScheme(Help.Ansi.ON));
        assertEquals(Help.Ansi.ON.new Text(String.format(
                "@|bold <best-app-ever>|@ [@|yellow --another-long-option-name|@=@|italic ^[<another-long-option-value>]|@]%n" +
                        "                [@|yellow --fourth-long-option-name|@=@|italic <fourth-long-option-value>|@]%n" +
                        "                [@|yellow --long-option@-name|@=@|italic <long-option-valu@@e>|@]%n" +
                        "                [@|yellow --third-long-option-name|@=@|italic <third-long-option-value>|@]%n")),
                help.synopsis(0));
    }
