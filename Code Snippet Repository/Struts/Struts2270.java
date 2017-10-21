    public void testFormWithTopLabelPositionWithElementOverride() throws Exception {
        FormTag form = new FormTag();
        form.setTheme("xhtml");
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

        verify(FormTag.class.getResource("Formtag-27.txt"));
    }
