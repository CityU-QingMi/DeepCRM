    @Test
    public void testShowDefaultValuesArrayField() throws Exception {
        @CommandLine.Command(showDefaultValues = true)
        class Params {
            @Option(names = {"-x", "--array"}, required = true, description = "the array")
            int[] array = {1, 5, 11, 23};
        }
        String result = usageString(new Params(), Help.Ansi.OFF);
        assertEquals(format("" +
                "Usage: <main class> -x[=<array>...]%n" +
                "  -x, --array[=<array>...]    the array%n" +
                "                                Default: [1, 5, 11, 23]%n"), result);
    }
