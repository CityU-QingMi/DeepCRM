    public void testFormWithContext() throws Exception {
        request.setupGetContext("/testNamespace");

        FormTag tag = new FormTag();
        tag.setTheme("xhtml");
        tag.setPageContext(pageContext);
        tag.setAction("testAction");
        tag.doStartTag();
        tag.doEndTag();


        verify(FormTag.class.getResource("Formtag-13.txt"));
    }
