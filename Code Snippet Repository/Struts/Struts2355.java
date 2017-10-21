    public void testNameEvaluation() throws Exception {
        TestAction testAction = (TestAction) action;
        testAction.setArray(new String[]{"test", "bar"});
        testAction.setFooInt(1);

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setName("array[%{fooInt}]");

        tag.doStartTag();
        tag.doEndTag();

        verify(TextFieldTag.class.getResource("Textfield-14.txt"));
    }
