    public void testIdIsEvaluatedAgainstStack1() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("myFooValue");

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("foo");
        tag.setId("%{foo}");

        tag.doStartTag();
        tag.doEndTag();

        verify(ComponentTag.class.getResource("Component-2.txt"));
    }
