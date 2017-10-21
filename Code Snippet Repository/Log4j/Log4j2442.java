    @Test
    public void testUsageParamLabels() throws Exception {
        @Command()
        class ParamLabels {
            @Option(names = "-f", paramLabel = "FILE", description = "a file") File f;
            @Option(names = "-n", description = "a number") int number;
            @Parameters(index = "0", paramLabel = "NUM", description = "number param") int n;
            @Parameters(index = "1", description = "the host") InetAddress host;
        }
        String result = usageString(new ParamLabels(), Help.Ansi.OFF);
        assertEquals(format("" +
                        "Usage: <main class> [-f=FILE] [-n=<number>] NUM <host>%n" +
                        "      NUM                     number param%n" +
                        "      host                    the host%n" +
                        "  -f= FILE                    a file%n" +
                        "  -n= <number>                a number%n",
                ""), result);
    }
