    public static void main(final String[] args) {
        // src/test/resources/log4j2-console-progress.xml
        // target/test-classes/log4j2-progress-console.xml
        try (final LoggerContext ctx = Configurator.initialize(ProgressConsoleTest.class.getName(),
                "target/test-classes/log4j2-progress-console.xml")) {
            for (double i = 0; i <= 1; i = i + 0.05) {
                updateProgress(i);
                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
