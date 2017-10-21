    public void testI18nComponentDisposeItselfFromComponentStack() throws Exception {
        stack.getContext().put(ActionContext.LOCALE, Locale.getDefault());

        TextFieldTag t = new TextFieldTag();
        t.setPageContext(pageContext);
        t.setName("textFieldName");

        container.getInstance(LocalizedTextProvider.class).addDefaultResourceBundle("org.apache.struts2.components.temp");

        I18nTag tag = new I18nTag();
        tag.setName("org.apache.struts2.components.tempo");
        tag.setPageContext(pageContext);

        try {
            t.doStartTag();
            tag.doStartTag();
            assertEquals(tag.getComponent().getComponentStack().peek(), tag.getComponent());
            tag.doEndTag();
            assertEquals(t.getComponent().getComponentStack().peek(), t.getComponent());
            t.doEndTag();
        }
        catch(Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
