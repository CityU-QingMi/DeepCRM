    @Test
    public void testDoEndTagStringWithParameters() throws Exception {
        this.setUp(Level.FATAL);

        this.tag.setDynamicAttribute(null, null, "A");
        this.tag.setDynamicAttribute(null, null, TimeUnit.HOURS);
        this.tag.setMessage("Test message with [{}] parameter of [{}]");

        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, this.tag.doEndTag());
        verify("Test message with [A] parameter of [HOURS] FATAL M- E");

    }
