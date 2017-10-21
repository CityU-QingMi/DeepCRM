    public void testWithJustKeyValueFromStack() throws Exception {
        TestAction testAction = (TestAction) action;
        final String key = "labelKey";
        final String value = "baz";

        // put key with message in a "resource"
        testAction.setText(key, value);

        LabelTag tag = new LabelTag();
        tag.setPageContext(pageContext);
        tag.setKey(key);
        tag.setTheme("simple");

        tag.doStartTag();
        tag.doEndTag();

        verify(LabelTest.class.getResource("Label-6.txt"));
    }
