    static CommandLine mainCommand() {
        CommandLine commandLine = new CommandLine(new Git());
        commandLine.addSubcommand("status", new GitStatus());
        commandLine.addSubcommand("commit", new GitCommit());
        commandLine.addSubcommand("add", new GitAdd());
        commandLine.addSubcommand("branch", new GitBranch());
        commandLine.addSubcommand("checkout", new GitCheckout());
        commandLine.addSubcommand("clone", new GitClone());
        commandLine.addSubcommand("diff", new GitDiff());
        commandLine.addSubcommand("merge", new GitMerge());
        commandLine.addSubcommand("push", new GitPush());
        commandLine.addSubcommand("rebase", new GitRebase());
        commandLine.addSubcommand("tag", new GitTag());
        return commandLine;
    }
