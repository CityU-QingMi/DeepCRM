    public void testWithCssErrorStyle() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        testAction.addFieldError("myname", "error");

        LabelTag tag = new LabelTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("myname");
        tag.setTitle("mytitle");
        tag.setValue("%{foo}");
        tag.setCssErrorStyle("cssErrorStyle1");

        tag.doStartTag();
        tag.doEndTag();

        verify(LabelTest.class.getResource("Label-8.txt"));
    }
