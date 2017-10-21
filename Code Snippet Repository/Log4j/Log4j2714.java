    @Test
    public void testFlush() throws IOException {
        final OutputStream os = mock(OutputStream.class);

        try (final OutputStream filteredOut =
            IoBuilder.forLogger(getExtendedLogger())
                .filter(os)
                .setLevel(LEVEL)
                .buildOutputStream()) {
          filteredOut.flush();
        }

        then(os).should().flush();
        then(os).should().close();
        then(os).shouldHaveNoMoreInteractions();
    }
