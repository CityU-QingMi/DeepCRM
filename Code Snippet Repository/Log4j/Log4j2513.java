    @Test
    public void testSeparatorCanBeSetDeclaratively() {
        @Command(separator = ":")
        class App {
            @Option(names = "--opt", required = true) String opt;
        }
        try {
            CommandLine.populateCommand(new App(), "--opt=abc");
            fail("Expected failure with unknown separator");
        } catch (UnmatchedArgumentException ok) {
            assertEquals("Unmatched argument [--opt=abc]", ok.getMessage());
        }
    }
