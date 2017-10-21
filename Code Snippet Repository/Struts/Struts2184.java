    public void testActionErrorsDontEscape() throws Exception {

        ActionErrorTag tag = new ActionErrorTag();
        TestAction testAction = new TestAction();
        testAction.addActionError("<p>hey</p>");
        stack.pop();
        stack.push(testAction);
        tag.setEscape(false);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        assertEquals(normalize("<ul class=\"errorMessage\"><li><span><p>hey</p></span></li></ul>", true),
                normalize(writer.toString(), true));
    }
