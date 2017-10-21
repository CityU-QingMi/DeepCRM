    public void testFieldErrorsDontEscape() throws Exception {

        FieldErrorTag tag = new FieldErrorTag();
        TestAction testAction = new TestAction();
        testAction.addFieldError("f", "<p>hey</p>");
        stack.pop();
        stack.push(testAction);
        tag.setEscape(false);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        assertEquals(normalize("<ul class=\"errorMessage\"><li><span><p>hey</p></span></li></ul>", true),
                normalize(writer.toString(), true));
    }
