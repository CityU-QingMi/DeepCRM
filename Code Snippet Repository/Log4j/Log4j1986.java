    public static void main(final String[] args) throws Exception {
        final long start = System.nanoTime();
        
        final List<Setup> tests = selectTests();
        runPerfTests(args, tests);
        
        System.out.printf("Done. Total duration: %.1f minutes%n", (System.nanoTime() - start)
                / (60.0 * 1000.0 * 1000.0 * 1000.0));

        printRanking(tests.toArray(new Setup[tests.size()]));
    }
