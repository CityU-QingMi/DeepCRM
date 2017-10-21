    public void doMain(final String[] args) throws Exception {
        final String runnerClass = args[0];
        final IPerfTestRunner runner = Loader.newCheckedInstanceOf(runnerClass, IPerfTestRunner.class);
        final String name = args[1];
        final String resultFile = args.length > 2 ? args[2] : null;
        for (final String arg : args) {
            if (verbose && throughput) {
                break;
            }
            if ("-verbose".equalsIgnoreCase(arg)) {
                verbose = true;
            }
            if ("-throughput".equalsIgnoreCase(arg)) {
                throughput = true;
            }
        }
        final int threadCount = 1;
        printf("Starting %s %s (%d)...%n", getClass().getSimpleName(), name, threadCount);
        runTestAndPrintResult(runner, name, threadCount, resultFile);
        runner.shutdown();
        System.exit(0);
    }
