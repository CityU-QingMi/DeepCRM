    public void displayHelp( PrintStream stdout )
    {
        stdout.println();

        PrintWriter pw = new PrintWriter( stdout );

        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp( pw, HelpFormatter.DEFAULT_WIDTH, "mvn [options] [<goal(s)>] [<phase(s)>]", "\nOptions:",
                             options, HelpFormatter.DEFAULT_LEFT_PAD, HelpFormatter.DEFAULT_DESC_PAD, "\n", false );

        pw.flush();
    }
