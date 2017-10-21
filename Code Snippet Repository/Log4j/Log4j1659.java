    @Test
    public void testHostname() {
        final ConsoleAppender app = context.getRequiredAppender("console", ConsoleAppender.class);
        final Layout<?> layout = app.getLayout();
        assertNotNull("No Layout", layout);
        assertTrue("Layout is not a PatternLayout", layout instanceof PatternLayout);
        final String pattern = ((PatternLayout) layout).getConversionPattern();
        assertNotNull("No conversion pattern", pattern);
        assertTrue("No filters", pattern.contains("org.junit,org.apache.maven,org.eclipse,sun.reflect,java.lang.reflect"));
    }
