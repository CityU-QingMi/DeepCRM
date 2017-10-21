    @Test
    public void testOverwrittenOptionDisallowedByDefault() {
        class App {
            @Option(names = "-s") String string;
            @Option(names = "-v") boolean bool;
        }
        try {
            CommandLine.populateCommand(new App(), "-s", "1", "-s", "2");
            fail("expected exception");
        } catch (OverwrittenOptionException ex) {
            assertEquals("option '-s' (string) should be specified only once", ex.getMessage());
        }
        try {
            CommandLine.populateCommand(new App(), "-v", "-v");
            fail("expected exception");
        } catch (OverwrittenOptionException ex) {
            assertEquals("option '-v' (bool) should be specified only once", ex.getMessage());
        }
    }
