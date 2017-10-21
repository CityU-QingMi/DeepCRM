    public void run() {
        if (!runTests &&
                !showSimpleExample &&
                !showAnsiInDescription &&
                !showIndexedColorPalette &&
                !showRgbColorPalette &&
                !showUsageForMainCommand &&
                !showUsageForSubcommandGitCommit &&
                !showUsageForSubcommandGitStatus) {
            CommandLine.usage(this, System.err);
            return;
        }
        if (runTests)                        { testParseSubCommands(); System.out.println("Ran tests OK.");}
        if (showSimpleExample)               { showSimpleExampleUsage(); }
        if (showAnsiInDescription)           { showAnsiInDescription(); }
        if (showIndexedColorPalette)         { showIndexedColorPalette(); }
        if (showRgbColorPalette)             { showRgbColorPalette(); }
        if (showUsageForMainCommand)         { testUsageMainCommand(); }
        if (showUsageForSubcommandGitStatus) { testUsageSubCommandStatus(); }
        if (showUsageForSubcommandGitCommit) { testUsageSubCommandCommit(); }
    }
