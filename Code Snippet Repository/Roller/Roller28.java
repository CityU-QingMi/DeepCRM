    public void testCleanTextKey() {
        assertEquals(null,UIAction.cleanTextKey(null));
        assertEquals("",UIAction.cleanTextKey(""));
        assertEquals("a",UIAction.cleanTextKey("a"));
        assertEquals("$",UIAction.cleanTextKey("$"));
        assertEquals("%",UIAction.cleanTextKey("%"));
        assertEquals("%$",UIAction.cleanTextKey("%$"));
        assertEquals("{",UIAction.cleanTextKey("{"));
        assertEquals("}",UIAction.cleanTextKey("}"));
        assertEquals("",UIAction.cleanTextKey("${"));
        assertEquals("",UIAction.cleanTextKey("%{"));
        assertEquals("text$",UIAction.cleanTextKey("text$"));
        assertEquals("text%",UIAction.cleanTextKey("text%"));
        assertEquals("", UIAction.cleanTextKey("something ${foo} more"));
        assertEquals("", UIAction.cleanTextKey("something %{foo} more"));
        assertEquals("", UIAction.cleanTextKey("something %{foo} more"));
    }
