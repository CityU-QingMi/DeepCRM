    @Test
    public void testSystemPropertiesOverrideExplicitColorScheme() {
        @CommandLine.Command(separator = "=") class App {
            @Option(names = {"--verbose", "-v"}) boolean verbose;
            @Option(names = {"--count", "-c"}) int count;
            @Option(names = {"--help", "-h"}, hidden = true) boolean helpRequested;
            @Parameters(paramLabel = "FILE", arity = "1..*") File[] files;
        }
        Help.Ansi ansi = Help.Ansi.ON;
        ColorScheme explicit = new ColorScheme(ansi)
                .commands(Style.faint, Style.bg_magenta)
                .options(Style.bg_red)
                .parameters(Style.reverse)
                .optionParams(Style.bg_green);
        // default color scheme
        assertEquals(ansi.new Text("@|faint,bg(magenta) <main class>|@ [@|bg(red) -v|@] [@|bg(red) -c|@=@|bg(green) <count>|@] @|reverse FILE|@ [@|reverse FILE|@...]" + LINESEP),
                new Help(new App(), explicit).synopsis(0));

        System.setProperty("picocli.color.commands", "blue");
        System.setProperty("picocli.color.options", "blink");
        System.setProperty("picocli.color.parameters", "red");
        System.setProperty("picocli.color.optionParams", "magenta");
        assertEquals(ansi.new Text("@|blue <main class>|@ [@|blink -v|@] [@|blink -c|@=@|magenta <count>|@] @|red FILE|@ [@|red FILE|@...]" + LINESEP),
                new Help(new App(), explicit).synopsis(0));
    }
