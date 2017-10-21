    public void testImageWithSrc() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("bar");

        SubmitTag tag = new SubmitTag();
        tag.setPageContext(pageContext);
        tag.setType("image");
        tag.setName("myname");
        tag.setLabel("mylabel");
        tag.setValue("%{foo}");
        tag.setSrc("some.gif");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Submit-6.txt"));
    }
