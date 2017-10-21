    @Test
    public void testDoEndTagStringMessageMarkerNoException() throws Exception {
        this.setUp(Level.INFO);

        this.tag.setMarker(MarkerManager.getMarker("E01"));
        this.tag.setMessage("Goodbye message for testDoEndTagStringMessageMarkerNoException");

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Goodbye message for testDoEndTagStringMessageMarkerNoException INFO M-E01 E");
    }
