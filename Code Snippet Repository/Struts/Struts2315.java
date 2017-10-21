    public void testImageWithExpressionSrc() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        ResetTag tag = new ResetTag();
        tag.setPageContext(pageContext);
        tag.setType("button");
        tag.setName("myname");
        tag.setLabel("mylabel");
        tag.setValue("%{foo}");
        tag.setSrc("%{getText(\"some.image.from.properties\")}");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Reset-6.txt"));
    }
