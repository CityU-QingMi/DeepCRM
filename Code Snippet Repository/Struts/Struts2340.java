    public void testSimple() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        SubmitTag tag = new SubmitTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("%{foo}");
        tag.setDisabled("true");
        tag.setTabindex("1");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Submit-1.txt"));
    }
