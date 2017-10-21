    @Test
    public void testSubclassAttributesOverrideEmptySuper() {
        @Command
        class EmptyBase {}
        @Command(name = "base", abbreviateSynopsis = true, commandListHeading = "c o m m a n d s",
                customSynopsis = "cust", description = "base description", descriptionHeading = "base descr heading",
                footer = "base footer", footerHeading = "base footer heading",
                header = "base header", headerHeading = "base header heading",
                optionListHeading = "base option heading", parameterListHeading = "base param heading",
                requiredOptionMarker = '&', separator = ";", showDefaultValues = true,
                sortOptions = false, synopsisHeading = "abcd")
        class FullBase extends EmptyBase{ }
        Help help = new Help(new FullBase());
        assertEquals("base", help.commandName);
        assertEquals(String.format("cust%n"), help.synopsis(0));
        assertEquals(String.format("cust%n"), help.customSynopsis());
        assertEquals(String.format("base%n"), help.abbreviatedSynopsis());
        assertEquals(String.format("base%n"), help.detailedSynopsis(0, null, true));
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
        assertTrue(help.abbreviateSynopsis);
        assertTrue(help.showDefaultValues);
        assertFalse(help.sortOptions);
        assertEquals(";", help.separator);
        assertEquals('&', help.requiredOptionMarker.charValue());
    }
