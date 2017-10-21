    public void testWithNullFieldErrors() throws Exception {
        FieldErrorTag tag = new FieldErrorTag();
        ((InternalAction)action).setHaveFieldErrors(false);
        ((InternalAction)action).setReturnNullForFieldErrors(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
            ParamTag pTag1 = new ParamTag();
            pTag1.setPageContext(pageContext);
            pTag1.setValue("%{'field2'}");
            pTag1.doStartTag();
            pTag1.doEndTag();

        tag.doEndTag();

        verify(FieldErrorTagTest.class.getResource("fielderror-2.txt"));
    }
