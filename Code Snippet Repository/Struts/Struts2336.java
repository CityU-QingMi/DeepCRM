    public void testButtonSimpleWithBody() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        SubmitTag tag = new SubmitTag();
        tag.setTheme("simple");
        tag.setPageContext(pageContext);
        tag.setType("button");
        tag.setName("myname");
        tag.setLabel("yoyoyoyoy");
        tag.setValue("%{foo}");

        StrutsBodyContent body = new StrutsBodyContent(null);
        body.print("foo");
        tag.setBodyContent(body);
        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Submit-10.txt"));
    }
