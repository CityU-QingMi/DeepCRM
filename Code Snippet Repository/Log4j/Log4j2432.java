    @Test
    public void testAttributesInheritedWhenSubclassingForReuse() throws UnsupportedEncodingException {
        @Command
        class EmptySub extends Base {}
        Help help = new Help(new EmptySub());
        assertEquals("base", help.commandName);
        assertEquals(String.format("cust%n"), help.synopsis(0));
        assertEquals(String.format("cust%n"), help.customSynopsis());
        assertEquals(String.format("base%n"), help.abbreviatedSynopsis());
        assertEquals(String.format("base%n"), help.detailedSynopsis(0,null, true));
        assertEquals("abcd", help.synopsisHeading);
        assertEquals("", help.commandList());
        assertEquals("c o m m a n d s", help.commandListHeading);
        assertEquals(String.format("base description%n"), help.description());
        assertEquals("base descr heading", help.descriptionHeading);
        assertEquals(String.format("base footer%n"), help.footer());
        assertEquals("base footer heading", help.footerHeading);
        assertEquals(String.format("base header%n"), help.header());
        assertEquals("base header heading", help.headerHeading);
        assertEquals("", help.optionList());
        assertEquals("base option heading", help.optionListHeading);
        assertEquals("", help.parameterList());
        assertEquals("base param heading", help.parameterListHeading);

        // these values NOT inherited!!
        assertEquals("=", help.separator);
        assertEquals(' ', help.requiredOptionMarker.charValue());
        assertFalse(help.abbreviateSynopsis);
        assertFalse(help.showDefaultValues);
        assertTrue(help.sortOptions);
    }
