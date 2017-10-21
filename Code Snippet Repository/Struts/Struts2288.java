     public void testWithKeyValueFromStack() throws Exception {
        TestAction testAction = (TestAction) action;
        final String key = "labelKey";
        final String value = "baz";
        testAction.setText(key, value);

        testAction.setFoo("output");

        LabelTag tag = new LabelTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setFor("for");
        tag.setName("foo");
        tag.setKey(key);

        tag.doStartTag();
        tag.doEndTag();

        verify(LabelTest.class.getResource("Label-4.txt"));
    }
