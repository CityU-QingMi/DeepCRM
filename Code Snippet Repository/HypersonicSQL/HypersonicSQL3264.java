    public static void main(String[] sa) {

        if (sa.length > 0 && !sa[sa.length - 1].equals("-g")) {
            TestDbBackup.baseDir = new File(sa[0]);

            if (baseDir.exists()) {
                throw new IllegalArgumentException(
                    "If you specify a work directory, it must not exist "
                    + "yet.  (This makes it much easier for us to clean up "
                    + "after ourselves).");
            }

            System.err.println("Using user-specified base dir: "
                               + baseDir.getAbsolutePath());
        }

        junit.textui.TestRunner runner = new junit.textui.TestRunner();
        junit.framework.TestResult result =
            runner.run(runner.getTest(TestDbBackup.class.getName()));

        System.exit(result.wasSuccessful() ? 0
                                           : 1);
    }
