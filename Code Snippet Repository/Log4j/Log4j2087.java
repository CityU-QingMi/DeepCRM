    private void verifyMethodsWithUnrolledVarargs(final ConcreteFilter filter, final Filter.Result expected) {
        final Logger logger = null;
        final Level level = null;
        final Marker marker = null;
        assertEquals(expected, filter.filter(logger, level, marker, "", 1));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4, 5));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4, 5, 6));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4, 5, 6, 7));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertEquals(expected, filter.filter(logger, level, marker, "", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
