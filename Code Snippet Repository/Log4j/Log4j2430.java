    @Test
    public void testUsageIndexedPositionalParameters() throws UnsupportedEncodingException {
        @Command()
        class App {
            @Parameters(index = "0", description = "source host") InetAddress host1;
            @Parameters(index = "1", description = "source port") int port1;
            @Parameters(index = "2", description = "destination host") InetAddress host2;
            @Parameters(index = "3..4", arity = "1..2", description = "destination port range") int[] port2range;
            @Parameters(index = "4..*", description = "files to transfer") String[] files;
            @Parameters(hidden = true) String[] all;
        }
        String actual = usageString(new App(), Help.Ansi.OFF);
        String expected = String.format(
                "Usage: <main class> <host1> <port1> <host2> <port2range> [<port2range>]%n" +
                "                    [<files>...]%n" +
                "      host1                   source host%n" +
                "      port1                   source port%n" +
                "      host2                   destination host%n" +
                "      port2range              destination port range%n" +
                "      files                   files to transfer%n"
        );
        assertEquals(expected, actual);
    }
