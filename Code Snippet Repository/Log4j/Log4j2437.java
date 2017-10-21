    @Test
    public void testUsageNestedSubcommand() throws IOException {
        @Command(name = "main") class MainCommand { @Option(names = "-a") boolean a; @Option(names = "-h", help = true) boolean h;}
        @Command(name = "cmd1") class ChildCommand1 { @Option(names = "-b") boolean b; }
        @Command(name = "cmd2") class ChildCommand2 { @Option(names = "-c") boolean c; @Option(names = "-h", help = true) boolean h;}
        @Command(name = "sub11") class GrandChild1Command1 { @Option(names = "-d") boolean d; }
        @Command(name = "sub12") class GrandChild1Command2 { @Option(names = "-e") int e; }
        @Command(name = "sub21") class GrandChild2Command1 { @Option(names = "-h", help = true) boolean h; }
        @Command(name = "sub22") class GrandChild2Command2 { @Option(names = "-g") boolean g; }
        @Command(name = "sub22sub1") class GreatGrandChild2Command2_1 {
            @Option(names = "-h", help = true) boolean h;
            @Option(names = {"-t", "--type"}) String customType;
        }
        CommandLine commandLine = new CommandLine(new MainCommand());
        commandLine
                .addSubcommand("cmd1", new CommandLine(new ChildCommand1())
                        .addSubcommand("sub11", new GrandChild1Command1())
                        .addSubcommand("sub12", new GrandChild1Command2())
                )
                .addSubcommand("cmd2", new CommandLine(new ChildCommand2())
                        .addSubcommand("sub21", new GrandChild2Command1())
                        .addSubcommand("sub22", new CommandLine(new GrandChild2Command2())
                                .addSubcommand("sub22sub1", new GreatGrandChild2Command2_1())
                        )
                );
        String main = usageString(commandLine, Help.Ansi.OFF);
        assertEquals(String.format("" +
                "Usage: main [-ah]%n" +
                "  -a%n" +
                "  -h%n" +
                "Commands:%n" +
                "  cmd1%n" +
                "  cmd2%n"), main);

        String cmd2 = usageString(commandLine.getSubcommands().get("cmd2"), Help.Ansi.OFF);
        assertEquals(String.format("" +
                "Usage: cmd2 [-ch]%n" +
                "  -c%n" +
                "  -h%n" +
                "Commands:%n" +
                "  sub21%n" +
                "  sub22%n"), cmd2);

        String sub22 = usageString(commandLine.getSubcommands().get("cmd2").getSubcommands().get("sub22"), Help.Ansi.OFF);
        assertEquals(String.format("" +
                "Usage: sub22 [-g]%n" +
                "  -g%n" +
                "Commands:%n" +
                "  sub22sub1%n"), sub22);
    }
