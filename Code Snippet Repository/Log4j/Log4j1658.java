    @Test
    public void testUpdateLoggersPropertyListeners() throws Exception {
        final LoggerContext ctx = context.getLoggerContext();
        ctx.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                assertEquals(LoggerContext.PROPERTY_CONFIG, evt.getPropertyName());
                assertSame(ctx, evt.getSource());
            }
        });
        ctx.updateLoggers();
    }
