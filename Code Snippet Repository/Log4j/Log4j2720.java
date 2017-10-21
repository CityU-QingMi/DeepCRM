    @Test
    public void testFlush() throws IOException {
        final OutputStream out = mock(OutputStream.class);

        try (final OutputStream filteredOut =
            IoBuilder.forLogger(getExtendedLogger())
                .filter(out)
                .setLevel(LEVEL)
                .buildOutputStream()) {
        	filteredOut.flush();
        }

        then(out).should().flush();
        then(out).should().close();
        then(out).shouldHaveNoMoreInteractions();
    }
