    public void testSimple_recursionTestNoValue() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("%{1+1}");

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("foo");
        tag.setSize("10");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Textfield-6.txt"));
    }
