    public void testCheckedWithTopLabelPosition() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setFoo("true");

        CheckboxTag tag = new CheckboxTag();
        tag.setPageContext(pageContext);
        tag.setId("someId");
        tag.setLabel("mylabel");
        tag.setName("foo");
        tag.setFieldValue("baz");
        tag.setOnfocus("test();");
        tag.setTitle("mytitle");
        tag.setLabelposition("top");

        tag.doStartTag();
        tag.doEndTag();

        verify(CheckboxTag.class.getResource("Checkbox-4.txt"));
    }
