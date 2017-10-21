    @Test
    public void testDoEndTagObjectNoMarkerException() throws Exception {
        this.setUp(Level.TRACE);

        this.tag.setException(new Exception("This is a test"));
        this.tag.setMessage(new MyMessage("Third message for testDoEndTagObjectNoMarkerException"));

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Third message for testDoEndTagObjectNoMarkerException TRACE M- E java.lang.Exception: This is a test");
    }
