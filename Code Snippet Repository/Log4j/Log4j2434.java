    @Test
    public void testSubclassAttributesOverrideSuperValues() {
        @Command(name = "sub", abbreviateSynopsis = false, commandListHeading = "subc o m m a n d s",
                customSynopsis = "subcust", description = "sub description", descriptionHeading = "sub descr heading",
                footer = "sub footer", footerHeading = "sub footer heading",
                header = "sub header", headerHeading = "sub header heading",
                optionListHeading = "sub option heading", parameterListHeading = "sub param heading",
                requiredOptionMarker = '%', separator = ":", showDefaultValues = false,
                sortOptions = true, synopsisHeading = "xyz")
        class FullSub extends Base{ }
        Help help = new Help(new FullSub());
        assertEquals("sub", help.commandName);
        assertEquals(String.format("subcust%n"), help.synopsis(0));
        assertEquals(String.format("subcust%n"), help.customSynopsis());
        assertEquals(String.format("sub%n"), help.abbreviatedSynopsis());
        assertEquals(String.format("sub%n"), help.detailedSynopsis(0,null, true));
        assertEquals("xyz", help.synopsisHeading);
        assertEquals("", help.commandList());
        assertEquals("subc o m m a n d s", help.commandListHeading);
        assertEquals(String.format("sub description%n"), help.description());
        assertEquals("sub descr heading", help.descriptionHeading);
        assertEquals(String.format("sub footer%n"), help.footer());
        assertEquals("sub footer heading", help.footerHeading);
        assertEquals(String.format("sub header%n"), help.header());
        assertEquals("sub header heading", help.headerHeading);
        assertEquals("", help.optionList());
        assertEquals("sub option heading", help.optionListHeading);
        assertEquals("", help.parameterList());
        assertEquals("sub param heading", help.parameterListHeading);
        assertFalse(help.abbreviateSynopsis);
        assertFalse(help.showDefaultValues);
        assertTrue(help.sortOptions);
        assertEquals(":", help.separator);
        assertEquals('%', help.requiredOptionMarker.charValue());
    }
