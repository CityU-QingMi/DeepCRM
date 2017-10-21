    @Test
    public void testDoEndTagObjectMarkerNoException() throws Exception {
        this.setUp(Level.WARN);

        this.tag.setMarker(MarkerManager.getMarker("E01"));
        this.tag.setMessage(new MyMessage("Another message for testDoEndTagObjectMarkerNoException"));

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Another message for testDoEndTagObjectMarkerNoException WARN M-E01 E");
    }
