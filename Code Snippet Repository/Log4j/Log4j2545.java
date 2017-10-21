    @Test
    public void testCommandListReturnsRegisteredCommands() {
        @Command class MainCommand {}
        @Command class Command1 {}
        @Command class Command2 {}
        CommandLine commandLine = new CommandLine(new MainCommand());
        commandLine.addSubcommand("cmd1", new Command1()).addSubcommand("cmd2", new Command2());

        Map<String, CommandLine> commandMap = commandLine.getSubcommands();
        assertEquals(2, commandMap.size());
        assertTrue("cmd1", commandMap.get("cmd1").getCommand() instanceof Command1);
        assertTrue("cmd2", commandMap.get("cmd2").getCommand() instanceof Command2);
    }
