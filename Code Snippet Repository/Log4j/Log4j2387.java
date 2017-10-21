    @Test
    public void testWithoutShowDefaultValues() throws Exception {
        @CommandLine.Command()
        class Params {
            @Option(names = {"-f", "--file"}, required = true, description = "the file to use") File file;
        }
        String result = usageString(new Params(), Help.Ansi.OFF);
        assertEquals(format("" +
                        "Usage: <main class> -f=<file>%n" +
                        "  -f, --file=<file>           the file to use%n",
                ""), result);
    }
