    public void testWithNoValue() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("baz");

        LabelTag tag = new LabelTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("foo");
        tag.setFor("for");

        tag.doStartTag();
        tag.doEndTag();

        verify(LabelTest.class.getResource("Label-5.txt"));
    }
