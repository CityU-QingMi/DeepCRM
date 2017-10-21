    public void testWithParamsWithFieldErrors3() throws Exception {
        FieldErrorTag tag = new FieldErrorTag();
        ((InternalAction)action).setHaveFieldErrors(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
            ParamTag pTag1 = new ParamTag();
            pTag1.setPageContext(pageContext);
            pTag1.setValue("%{'field2'}");
            pTag1.doStartTag();
            pTag1.doEndTag();

        tag.doEndTag();

        verify(FieldErrorTagTest.class.getResource("fielderror-5.txt"));
    }
