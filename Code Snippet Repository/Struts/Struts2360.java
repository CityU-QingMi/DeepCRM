    public void testSimple_recursionTest() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("%{1+1}");

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("%{foo}");
        tag.setSize("10");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Textfield-5.txt"));
    }
