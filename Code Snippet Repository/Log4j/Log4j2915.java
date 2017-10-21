    public static void main(final String[] args) throws Exception {
        if ("Classic".equalsIgnoreCase(args[0])) {
            final ClassicLogger logger = new ClassicLogger();
            runTestSuite(logger);
        } else if ("NoGC".equalsIgnoreCase(args[0])) {
            final NoGcLogger logger = new NoGcLogger();
            runTestSuite(logger);
        } else {
            throw new IllegalArgumentException("Specify either Classic or NoGC");
//            ClassicLogger classic = new ClassicLogger();
//            NoGcLogger nogc = new NoGcLogger();
//            doTestRunBoth(classic, nogc, REPETITIONS, 0);
        }
        reportResults(args[0]);
    }
