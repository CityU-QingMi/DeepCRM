    public void testTextTagUsesLocaleFromValueStack() throws JspException {
        stack.pop();
        stack.push(container.inject(TestAction1.class));

        Locale defaultLocale = getDefaultLocale();
        Locale foreignLocale = getForeignLocale();
        assertNotSame(defaultLocale, foreignLocale);

        ActionContext.getContext().setLocale(defaultLocale);
        String key = "simpleKey";
        String value_default = getLocalizedMessage(defaultLocale);
        tag.setName(key);
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(value_default, writer.toString());

        final StringBuffer buffer = writer.getBuffer();
        buffer.delete(0, buffer.length());
        String value_int = getLocalizedMessage(foreignLocale);
        assertFalse(value_default.equals(value_int));
        ValueStack newStack = container.getInstance(ValueStackFactory.class).createValueStack(stack);
        newStack.getContext().put(ActionContext.LOCALE, foreignLocale);
        newStack.getContext().put(ActionContext.CONTAINER, container);
        assertNotSame(newStack.getContext().get(ActionContext.LOCALE), ActionContext.getContext().getLocale());
        request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, newStack);
        assertEquals(ActionContext.getContext().getValueStack().peek(), newStack.peek());
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(value_int, writer.toString());
    }
