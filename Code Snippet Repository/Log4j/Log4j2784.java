    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // All 9 JUL levels, All 8 Log4j levels and extras
                // @formatter:off
                {java.util.logging.Level.OFF, Level.OFF},
                {java.util.logging.Level.SEVERE, Level.ERROR},
                {java.util.logging.Level.WARNING, Level.WARN},
                {java.util.logging.Level.INFO, Level.INFO},
                {java.util.logging.Level.CONFIG, LevelTranslator.CONFIG},
                {java.util.logging.Level.FINE, Level.DEBUG},
                {java.util.logging.Level.FINER, Level.TRACE},
                {java.util.logging.Level.FINEST, LevelTranslator.FINEST},
                {java.util.logging.Level.ALL, Level.ALL}
                // @formatter:on
                });
    }
