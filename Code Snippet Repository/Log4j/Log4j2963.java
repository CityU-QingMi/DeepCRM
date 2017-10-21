    @Test
    public void testDoEndTagDefaultPageScope() throws Exception {
        this.context.setAttribute("testAttribute01", "testValue01", PageContext.PAGE_SCOPE);
        this.context.setAttribute("anotherAttribute02", "finalValue02", PageContext.PAGE_SCOPE);
        this.context.setAttribute("badAttribute03", "skippedValue03", PageContext.SESSION_SCOPE);

        final int returnValue = this.tag.doEndTag();
        assertEquals("The return value is not correct.", Tag.EVAL_PAGE, returnValue);

        this.writer.flush();
        final String output = new String(this.output.toByteArray(), UTF8);
        assertEquals("The output is not correct.",
                "<dl>" +
                        "<dt><code>testAttribute01</code></dt><dd><code>testValue01</code></dd>" +
                        "<dt><code>anotherAttribute02</code></dt><dd><code>finalValue02</code></dd>" +
                        "</dl>", output);
    }
