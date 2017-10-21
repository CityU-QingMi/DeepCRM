    public void testSimple() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        ResetTag tag = new ResetTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("%{foo}");
        tag.setTabindex("1");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Reset-1.txt"));
    }
