    public void testTextTagUsesValueStackInRequestNotActionContext() throws JspException {
        String key = "simpleKey";
        String value1 = "Simple Message";
        Locale foreignLocale = getForeignLocale();
        String value2 = getLocalizedMessage(foreignLocale);
        tag.setName(key);
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(value1, writer.toString());
        final StringBuffer buffer = writer.getBuffer();
        buffer.delete(0, buffer.length());
        ValueStack newStack = container.getInstance(ValueStackFactory.class).createValueStack();
        newStack.getContext().put(ActionContext.LOCALE, foreignLocale);
        newStack.getContext().put(ActionContext.CONTAINER, container);
        newStack.push(container.inject(TestAction1.class));
        request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, newStack);
        assertNotSame(ActionContext.getContext().getValueStack().peek(), newStack.peek());

        tag.doStartTag();
        tag.doEndTag();
        assertEquals(value2, writer.toString());
    }
