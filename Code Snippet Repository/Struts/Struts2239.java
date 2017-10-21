     public void testFieldErrorsEscape() throws Exception {

        FieldErrorTag tag = new FieldErrorTag();
        TestAction testAction = new TestAction();
        testAction.addFieldError("f", "<p>hey</p>");
        stack.pop();
        stack.push(testAction);
        tag.setEscape(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        assertEquals(normalize("<ul class=\"errorMessage\"><li><span>&lt;p&gt;hey&lt;/p&gt;</span></li></ul>", true),
                normalize(writer.toString(), true));
    }
