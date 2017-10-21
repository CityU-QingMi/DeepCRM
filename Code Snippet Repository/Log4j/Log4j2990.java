    @Test
    public void testDoEndTagMessageMarkerNoException() throws Exception {
        this.setUp(Level.WARN);

        this.tag.setMarker(MarkerManager.getMarker("E01"));
        this.tag.setMessage(
                logger.getMessageFactory().newMessage("Another message for testDoEndTagMessageMarkerNoException")
        );

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Another message for testDoEndTagMessageMarkerNoException WARN M-E01 E");
    }
