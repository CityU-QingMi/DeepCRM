    @Test
    public void testDoEndTagMessageMarkerException() throws Exception {
        this.setUp(Level.ERROR);

        this.tag.setException(new RuntimeException("This is another test"));
        this.tag.setMarker(MarkerManager.getMarker("F02"));
        this.tag.setMessage(
                logger.getMessageFactory().newMessage("Final message for testDoEndTagMessageMarkerException")
        );

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Final message for testDoEndTagMessageMarkerException ERROR M-F02 E java.lang.RuntimeException: " +
                "This is another test");
    }
