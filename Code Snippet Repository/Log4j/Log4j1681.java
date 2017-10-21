    static void test(final String[] args, final String config) {
        // System.out.println(System.getProperty("java.class.path"));
        try (final LoggerContext ctx = Configurator
                .initialize(ConsoleAppenderDefaultSuppressedThrowable.class.getName(), config)) {
            final IOException ioEx = new IOException("test suppressed");
            ioEx.addSuppressed(new IOException("test suppressed 1", new IOException("test 1")));
            final IOException ioEx2 = new IOException("test 2");
            ioEx2.addSuppressed(new IOException("test 3"));
            ioEx.addSuppressed(new IOException("test suppressed 2", ioEx2));
            final IOException e = new IOException("test", ioEx);
            LOG.error("Error message {}, suppressed?", "Hi", e);
            System.out.println("printStackTrace");
            e.printStackTrace();
        }
    }
