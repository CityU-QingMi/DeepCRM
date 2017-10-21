    @Test
    public void testDoEndTagStringMessageNoMarkerException() throws Exception {
        this.setUp(Level.ERROR);

        this.tag.setException(new Exception("This is a test"));
        this.tag.setMessage("Another message for testDoEndTagStringMessageNoMarkerException");

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Another message for testDoEndTagStringMessageNoMarkerException ERROR M- E java.lang.Exception: This is a test");
    }
