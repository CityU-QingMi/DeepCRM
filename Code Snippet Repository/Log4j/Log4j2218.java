    @Test
    public void testHeaderFooterJavaLookup() throws Exception {
        // % does not work here.
        final String pattern = "%d{UNIX} MyApp%n${java:version}%n${java:runtime}%n${java:vm}%n${java:os}%n${java:hw}";
        final PatternLayout layout = PatternLayout.newBuilder().withConfiguration(ctx.getConfiguration())
                .withHeader("Header: " + pattern).withFooter("Footer: " + pattern).build();
        final byte[] header = layout.getHeader();
        assertNotNull("No header", header);
        final String headerStr = new String(header);
        assertTrue(headerStr, headerStr.contains("Header: "));
        assertTrue(headerStr, headerStr.contains("Java version "));
        assertTrue(headerStr, headerStr.contains("(build "));
        assertTrue(headerStr, headerStr.contains(" from "));
        assertTrue(headerStr, headerStr.contains(" architecture: "));
        assertFalse(headerStr, headerStr.contains("%d{UNIX}"));
        //
        final byte[] footer = layout.getFooter();
        assertNotNull("No footer", footer);
        final String footerStr = new String(footer);
        assertTrue(footerStr, footerStr.contains("Footer: "));
        assertTrue(footerStr, footerStr.contains("Java version "));
        assertTrue(footerStr, footerStr.contains("(build "));
        assertTrue(footerStr, footerStr.contains(" from "));
        assertTrue(footerStr, footerStr.contains(" architecture: "));
        assertFalse(footerStr, footerStr.contains("%d{UNIX}"));
    }
