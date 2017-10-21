    public void testSimpleThemeInput() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        SubmitTag tag = new SubmitTag();
        tag.setPageContext(pageContext);
        tag.setTheme("simple");
        tag.setType("input");
        tag.setName("myname");
        tag.setLabel("mylabel");
        tag.setAction(null);
        tag.setMethod(null);

        tag.doStartTag();
        tag.doEndTag();

        assertEquals("<input type=\"submit\" value=\"Submit\" id=\"myname\" name=\"myname\"/>", writer.toString().trim());
    }
