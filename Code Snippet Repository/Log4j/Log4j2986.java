    @Test
    public void testDoEndTagStringMessageMarkerException() throws Exception {
        this.setUp(Level.TRACE);

        this.tag.setException(new RuntimeException("This is another test"));
        this.tag.setMarker(MarkerManager.getMarker("F02"));
        this.tag.setMessage("Final message for testDoEndTagStringMessageMarkerException");

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Final message for testDoEndTagStringMessageMarkerException TRACE M-F02 E java.lang.RuntimeException: This is another test");
    }
