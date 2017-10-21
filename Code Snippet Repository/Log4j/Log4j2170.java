    private static ThrowableFormatOptions test(final String[] options, final int expectedLines,
            final String expectedSeparator, final List<String> expectedPackages) {
        final ThrowableFormatOptions tfo = ThrowableFormatOptions.newInstance(options);
        assertEquals("getLines", expectedLines, tfo.getLines());
        assertEquals("getSeparator", expectedSeparator, tfo.getSeparator());
        assertEquals("getPackages", expectedPackages, tfo.getIgnorePackages());
        assertEquals("allLines", expectedLines == Integer.MAX_VALUE, tfo.allLines());
        assertEquals("anyLines", expectedLines != 0, tfo.anyLines());
        assertEquals("minLines", 0, tfo.minLines(0));
        assertEquals("minLines", expectedLines, tfo.minLines(Integer.MAX_VALUE));
        assertEquals("hasPackages", expectedPackages != null && !expectedPackages.isEmpty(), tfo.hasPackages());
        assertNotNull("toString", tfo.toString());
        return tfo;
    }
