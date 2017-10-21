    @Override
    protected void setUp() throws Exception {
        super.setUp();
        TestAction testAction = (TestAction) action;
        tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setId("myId");
        tag.setLabel("mylabel");
        tag.setName("foo");
        tag.setValue("bar");
        tag.setTitle("mytitle");

        testAction.addFieldError("foo", "bar error message");
    }
