    @Test
    public void testDoEndTagStringWithParametersMarkerAndException() throws Exception {
        this.setUp(Level.DEBUG);

        this.tag.setException(new Error("This is the last test"));
        this.tag.setMarker(MarkerManager.getMarker("N03"));
        this.tag.setDynamicAttribute(null, null, "Z");
        this.tag.setDynamicAttribute(null, null, TimeUnit.SECONDS);
        this.tag.setMessage("Final message with [{}] parameter of [{}]");

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Final message with [Z] parameter of [SECONDS] DEBUG M-N03 E java.lang.Error: This is the last test");

    }
