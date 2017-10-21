    @Test
    public void testUsageSeparator() throws Exception {
        @Command(showDefaultValues = true)
        class Params {
            @Option(names = {"-f", "--file"}, required = true, description = "the file to use") File file = new File("def.txt");
        }
        String result = usageString(new Params(), Help.Ansi.OFF);
        assertEquals(format("" +
                        "Usage: <main class> -f=<file>%n" +
                        "  -f, --file=<file>           the file to use%n" +
                        "                                Default: def.txt%n",
                ""), result);
    }
