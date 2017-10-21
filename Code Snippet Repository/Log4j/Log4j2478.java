    @Test
    public void testNoMissingRequiredParamErrorIfHelpOptionSpecified() {
        class App {
            @Parameters(hidden = true)  // "hidden": don't show this parameter in usage help message
                    List<String> allParameters; // no "index" attribute: captures _all_ arguments (as Strings)

            @Parameters(index = "0")    InetAddress  host;
            @Parameters(index = "1")    int          port;
            @Parameters(index = "2..*") File[]       files;

            @Option(names = "-?", help = true) boolean help;
        }
        CommandLine.populateCommand(new App(), new String[] {"-?"});
        try {
            CommandLine.populateCommand(new App(), new String[0]);
            fail("Should not accept missing mandatory parameter");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameters: host, port", ex.getMessage());
        }
    }
