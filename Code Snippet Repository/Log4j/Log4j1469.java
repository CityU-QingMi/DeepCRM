    public static <R extends Runnable> void run(R command, PrintStream out, Help.Ansi ansi, String... args) {
        CommandLine cmd = new CommandLine(command); // validate command outside of try-catch
        try {
            cmd.parse(args);
        } catch (Exception ex) {
            out.println(ex.getMessage());
            cmd.usage(out, ansi);
            return;
        }
        command.run();
    }
