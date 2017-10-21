    @Test
    public void testShowDefaultValues() throws Exception {
        @CommandLine.Command(showDefaultValues = true)
        class Params {
            @Option(names = {"-f", "--file"}, required = true, description = "the file to use")
            File file = new File("theDefault.txt");
        }
        String result = usageString(new Params(), Help.Ansi.OFF);
        assertEquals(format("" +
                "Usage: <main class> -f=<file>%n" +
                "  -f, --file=<file>           the file to use%n" +
                "                                Default: theDefault.txt%n"), result);
    }
