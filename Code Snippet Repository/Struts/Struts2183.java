     public void testActionErrorsEscape() throws Exception {

        ActionErrorTag tag = new ActionErrorTag();
        TestAction testAction = new TestAction();
        testAction.addActionError("<p>hey</p>");
        stack.pop();
        stack.push(testAction);
        tag.setEscape(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        assertEquals(normalize("<ul class=\"errorMessage\"><li><span>&lt;p&gt;hey&lt;/p&gt;</span></li></ul>", true),
                normalize(writer.toString(), true));
    }
