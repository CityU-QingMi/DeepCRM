    public void testSimpleThemeImageUsingActionOnly() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        SubmitTag tag = new SubmitTag();
        tag.setPageContext(pageContext);
        tag.setTheme("simple");
        tag.setType("button");
        tag.setName("myname");
        tag.setLabel("mylabel");
        tag.setAction("manager");
        tag.setMethod(null); // no method

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Submit-8.txt"));
    }
