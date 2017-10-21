    @Test
    public void testDeclarativelyAddSubcommandsFailsWithoutNoArgConstructor() {
        @Command(name = "sub1") class ABC {}
        @Command(subcommands = {ABC.class}) class MainCommand {}
        try {
            new CommandLine(new MainCommand());
        } catch (IllegalArgumentException ex) {
            String expected = String.format("Cannot instantiate subcommand %s: the class has no constructor", ABC.class.getName());
            assertEquals(expected, ex.getMessage());
        }
    }
