    private String collectRunOutput(List<String> commands) throws IOException, InterruptedException
    {
        StringBuilder cline = new StringBuilder();
        for (String command : commands)
        {
            cline.append(command).append(" ");
        }
        System.out.println("Command line: " + cline);

        ProcessBuilder builder = new ProcessBuilder(commands);
        // Set PWD
        builder.directory(MavenTestingUtils.getTestResourceDir("empty.home"));
        Process pid = builder.start();

        ConsoleCapture stdOutPump = new ConsoleCapture("STDOUT",pid.getInputStream()).start();
        ConsoleCapture stdErrPump = new ConsoleCapture("STDERR",pid.getErrorStream()).start();

        int exitCode = pid.waitFor();
        if (exitCode != 0)
        {
            System.out.printf("STDERR: [" + stdErrPump.getConsoleOutput() + "]%n");
            System.out.printf("STDOUT: [" + stdOutPump.getConsoleOutput() + "]%n");
            Assert.assertThat("Exit code",exitCode,is(0));
        }
        stdErrPump.getConsoleOutput();
        return stdOutPump.getConsoleOutput();
    }
