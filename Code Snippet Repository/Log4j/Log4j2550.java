    @Test
    public void testRunPrintsErrorIfParseFails() throws UnsupportedEncodingException {
        final boolean[] runWasCalled = {false};
        class App implements Runnable {
            @Option(names = "-number") int number;
            public void run() {
                runWasCalled[0] = true;
            }
        }
        PrintStream oldErr = System.err;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setErr(new PrintStream(baos, true, "UTF8"));
        CommandLine.run(new App(), System.err, "-number", "not a number");
        System.setErr(oldErr);

        String result = baos.toString("UTF8");
        assertFalse(runWasCalled[0]);
        assertEquals(String.format(
                "Could not convert 'not a number' to int for option '-number': java.lang.NumberFormatException: For input string: \"not a number\"%n" +
                "Usage: <main class> [-number=<number>]%n" +
                "      -number=<number>%n"), result);
    }
