    @Test
    public void testIfSeparatorSetTheDefaultSeparatorIsNotRecognizedWithUnmatchedArgsAllowed() {
        @Command(separator = ":")
        class App {
            @Option(names = "--opt", required = true) String opt;
        }
        CommandLine cmd = new CommandLine(new App()).setUnmatchedArgumentsAllowed(true);
        try {
            cmd.parse("--opt=abc");
        } catch (MissingParameterException ok) {
            assertEquals("Missing required option 'opt'", ok.getMessage());
        }
    }
