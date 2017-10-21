    @Test
    public void testDoEndTagSessionScope() throws Exception {
        this.context.setAttribute("otherAttribute03", "lostValue03", PageContext.PAGE_SCOPE);
        this.context.setAttribute("coolAttribute01", "weirdValue01", PageContext.SESSION_SCOPE);
        this.context.setAttribute("testAttribute02", "testValue02", PageContext.SESSION_SCOPE);

        this.tag.setScope("session");
        final int returnValue = this.tag.doEndTag();
        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, returnValue);

        this.writer.flush();
        final String output = new String(this.output.toByteArray(), UTF8);
        assertEquals("The output is not correct.",
                "<dl>" +
                        "<dt><code>coolAttribute01</code></dt><dd><code>weirdValue01</code></dd>" +
                        "<dt><code>testAttribute02</code></dt><dd><code>testValue02</code></dd>" +
                        "</dl>", output);
    }
