    public void testWithKeyNoValueFromStack() throws Exception {
        TestAction testAction = (TestAction) action;
        final String key = "labelKey";
        final String value = "baz";
        testAction.setText(key, value);

        testAction.setFoo("notToBeOutput");

        LabelTag tag = new LabelTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setFor("for");
        tag.setName("foo2");
        tag.setKey(key);

        tag.doStartTag();
        tag.doEndTag();

        verify(LabelTest.class.getResource("Label-2.txt"));
    }
