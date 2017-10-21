    @Test(timeout = 15000)
    public void testIssue148InfiniteLoop() throws Exception {
        @Command(showDefaultValues = true)
        class App {
            @Option(names = "--foo-bar-baz")
            String foo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
            // Default value needs to be at least 1 character larger than the "WRAP" column in TextTable(Ansi), which is
            // currently 51 characters. Going with 81 to be safe.
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        CommandLine.usage(new App(), printStream);

        String content = new String(output.toByteArray(), "UTF-8")
                .replaceAll("\r\n", "\n"); // Normalize line endings.

        String expectedOutput =
                "Usage: <main class> [--foo-bar-baz=<foo>]\n" +
                "      --foo-bar-baz=<foo>       Default:\n" +
                "                                aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "                                aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "\n";

        assertEquals(expectedOutput, content);
    }
