    @SuppressWarnings("")
    static void assertEqualLogEvents(final LogEvent expected, final LogEvent actual, final boolean includeSource,
            final boolean includeContext, final boolean includeStacktrace) {
        assertEquals(expected.getClass(), actual.getClass());
        assertEquals(includeContext ? expected.getContextData() : ContextDataFactory.createContextData(), actual.getContextData());
        assertEquals(includeContext ? expected.getContextMap() : Collections.EMPTY_MAP, actual.getContextMap());
        assertEquals(expected.getContextStack(), actual.getContextStack());
        assertEquals(expected.getLevel(), actual.getLevel());
        assertEquals(expected.getLoggerName(), actual.getLoggerName());
        assertEquals(expected.getLoggerFqcn(), actual.getLoggerFqcn());
        assertEquals(expected.getMarker(), actual.getMarker());
        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getTimeMillis(), actual.getTimeMillis());
        assertEquals(includeSource ? expected.getSource() : null, actual.getSource());
        assertEquals(expected.getThreadName(), actual.getThreadName());
        assertNotNull("original should have an exception", expected.getThrown());
        assertNull("exception should not be serialized", actual.getThrown());
        if (includeStacktrace) { // TODO should compare the rest of the ThrowableProxy
            assertEquals(expected.getThrownProxy(), actual.getThrownProxy());
        }
        assertEquals(expected.isEndOfBatch(), actual.isEndOfBatch());
        assertEquals(expected.isIncludeLocation(), actual.isIncludeLocation());

        // original: non-null thrown & null thrownProxy
        // deserialized: null thrown & non-null thrownProxy
        assertNotEquals(expected.hashCode(), actual.hashCode());
        assertNotEquals(expected, actual);
    }
