    public static void testParseSubCommands() {
        CommandLine commandLine = mainCommand();

        String[] args = { "--git-dir=/home/rpopma/picocli", "status", "-sbuno"};
        List<CommandLine> parsed = commandLine.parse(args);
        assert parsed.size() == 2 : "found 2 commands";

        assert parsed.get(0).getCommand().getClass() == Git.class;
        assert parsed.get(1).getCommand().getClass() == GitStatus.class;

        Git git = (Git) parsed.get(0).getCommand();
        assert git.gitDir.equals(new File("/home/rpopma/picocli"));

        GitStatus status = (GitStatus) parsed.get(1).getCommand();
        assert  status.shortFormat : "status -s";
        assert  status.branchInfo  : "status -b";
        assert !status.showIgnored : "status --showIgnored not specified";
        assert  status.mode == GitStatusMode.no : "status -u=no";
    }
