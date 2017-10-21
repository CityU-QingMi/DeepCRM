    public void testWithParamsWithFieldErrors2() throws Exception {
        FieldErrorTag tag = new FieldErrorTag();
        ((InternalAction)action).setHaveFieldErrors(true);
        tag.setPageContext(pageContext);
        tag.doStartTag();
            ParamTag pTag1 = new ParamTag();
            pTag1.setPageContext(pageContext);
            pTag1.setValue("%{'field1'}");
            pTag1.doStartTag();
            pTag1.doEndTag();

            ParamTag pTag2 = new ParamTag();
            pTag2.setPageContext(pageContext);
            pTag2.setValue("%{'field2'}");
            pTag2.doStartTag();
            pTag2.doEndTag();

        tag.doEndTag();

        verify(FieldErrorTagTest.class.getResource("fielderror-4.txt"));
    }
