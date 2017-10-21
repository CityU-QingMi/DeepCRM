    public void testFormWithTopLabelPositionWithElementOverrideCssXhtml() throws Exception {
        FormTag form = new FormTag();
        form.setTheme("css_xhtml");
        form.setAction("testAction");
        form.setPageContext(pageContext);
        form.setIncludeContext(false);
        form.setLabelposition("left");

        TextFieldTag text = new TextFieldTag();
        text.setPageContext(pageContext);
        text.setLabel("label");
        text.setLabelposition("top");

        form.doStartTag();
        text.doStartTag();
        text.doEndTag();
        form.doEndTag();

        verify(FormTag.class.getResource("Formtag-28.txt"));
    }
