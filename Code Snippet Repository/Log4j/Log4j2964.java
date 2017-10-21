    @Test
    public void testDoEndTagSessionScopeNoAttributes() throws Exception {
        this.context.setAttribute("badAttribute01", "skippedValue01", PageContext.PAGE_SCOPE);

        this.tag.setScope("session");
        final int returnValue = this.tag.doEndTag();
        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, returnValue);

        this.writer.flush();
        final String output = new String(this.output.toByteArray(), UTF8);
        assertEquals("The output is not correct.", "<dl></dl>", output);
    }
