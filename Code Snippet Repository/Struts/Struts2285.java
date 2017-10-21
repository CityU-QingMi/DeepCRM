    public void testSimpleWithLabelposition() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        LabelTag tag = new LabelTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setValue("%{foo}");
        tag.setLabelposition("top");

        tag.doStartTag();
        tag.doEndTag();

        verify(LabelTest.class.getResource("Label-3.txt"));
    }
