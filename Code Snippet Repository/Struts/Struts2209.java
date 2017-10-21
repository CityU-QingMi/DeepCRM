    public void testCheckedWithErrorStyle() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("true");
        testAction.addFieldError("foo", "Some Foo Error");
        testAction.addFieldError("foo", "Another Foo Error");

        CheckboxTag tag = new CheckboxTag();
        tag.setPageContext(pageContext);
        tag.setLabel("mylabel");
        tag.setName("foo");
        tag.setFieldValue("baz");
        tag.setOndblclick("test();");
        tag.setOnclick("test();");
        tag.setTitle("mytitle");
        tag.setCssErrorStyle("color:red");

        tag.doStartTag();
        tag.doEndTag();

        verify(CheckboxTag.class.getResource("Checkbox-33.txt"));
    }
