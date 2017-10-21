    @Test
    public void testOverwrittenOptionSetsLastValueIfAllowed() {
        class App {
            @Option(names = {"-s", "--str"})      String string;
            @Option(names = {"-v", "--verbose"}) boolean bool;
        }
        CommandLine commandLine = new CommandLine(new App()).setOverwrittenOptionsAllowed(true);
        commandLine.parse("-s", "1", "--str", "2");
        assertEquals("2", ((App) commandLine.getCommand()).string);

        commandLine = new CommandLine(new App()).setOverwrittenOptionsAllowed(true);
        commandLine.parse("-v", "--verbose", "-v"); // F -> T -> F -> T
        assertEquals(true, ((App) commandLine.getCommand()).bool);
    }
