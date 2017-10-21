    @Test
    public void testDoEndTagMessageNoMarkerNoException() throws Exception {
        this.setUp(Level.INFO);

        this.tag.setMessage(
                logger.getMessageFactory().newMessage("First message for testDoEndTagMessageNoMarkerNoException")
        );

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("First message for testDoEndTagMessageNoMarkerNoException INFO M- E");
    }
