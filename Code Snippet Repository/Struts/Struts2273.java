    public void testFormWithoutContext() throws Exception {
        request.setupGetContext("somecontext");

        FormTag tag = new FormTag();
        tag.setTheme("xhtml");
        tag.setPageContext(pageContext);
        tag.setAction("testAction");
        tag.setIncludeContext(false);
        tag.doStartTag();
        tag.doEndTag();


        verify(FormTag.class.getResource("Formtag-14.txt"));
    }
