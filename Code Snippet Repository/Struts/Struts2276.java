    public void testFormWithFocusElement() throws Exception {
        FormTag tag = new FormTag();
        tag.setTheme("xhtml");
        tag.setPageContext(pageContext);
        tag.setAction("testAction");
        tag.setFocusElement("felement");
        tag.doStartTag();
        tag.doEndTag();

        verify(FormTag.class.getResource("Formtag-12.txt"));
    }
