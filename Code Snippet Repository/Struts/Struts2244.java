     public void testWithFieldName() throws Exception {
        FieldErrorTag tag = new FieldErrorTag();
        tag.setFieldName("field1");
        ((InternalAction)action).setHaveFieldErrors(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
        tag.doEndTag();

        verify(FieldErrorTagTest.class.getResource("fielderror-6.txt"));
    }
