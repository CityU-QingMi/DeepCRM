    @Test
    public void testHeaderFooterMainLookup() {
        MainMapLookup.setMainArguments("value0", "value1", "value2");
        final PatternLayout layout = PatternLayout.newBuilder().withConfiguration(ctx.getConfiguration())
                .withHeader("${main:0}").withFooter("${main:2}").build();
        final byte[] header = layout.getHeader();
        assertNotNull("No header", header);
        final String headerStr = new String(header);
        assertTrue(headerStr, headerStr.contains("value0"));
        //
        final byte[] footer = layout.getFooter();
        assertNotNull("No footer", footer);
        final String footerStr = new String(footer);
        assertTrue(footerStr, footerStr.contains("value2"));
    }
