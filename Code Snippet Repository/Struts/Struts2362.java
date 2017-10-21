    public void testHtml5EmailTag() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("myemaillabel");
        tag.setName("foo");
        tag.setSize("50");
        tag.setType("email");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Textfield-7.txt"));
    }
