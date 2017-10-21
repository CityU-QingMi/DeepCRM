    @Test
    public void testDoEndTagMessageNoMarkerException() throws Exception {
        this.setUp(Level.TRACE);

        this.tag.setException(new Exception("This is a test"));
        this.tag.setMessage(
                logger.getMessageFactory().newMessage("Third message for testDoEndTagMessageNoMarkerException")
        );

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Third message for testDoEndTagMessageNoMarkerException TRACE M- E java.lang.Exception: This is a test");
    }
