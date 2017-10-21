     public void testTextTagCanSearchStackToFindValue() throws JspException {
        String key = "result";

        tag.setName(key);
        tag.setSearchValueStack("true");
        final StringBuffer buffer = writer.getBuffer();
        buffer.delete(0, buffer.length());
        ValueStack newStack = container.getInstance(ValueStackFactory.class).createValueStack();
        newStack.getContext().put(ActionContext.CONTAINER, container);
        TestAction testAction = new TestAction();
        container.inject(testAction);
        testAction.setResult("bar");
        newStack.push(testAction);
        request.setAttribute(ServletActionContext.STRUTS_VALUESTACK_KEY, newStack);


        tag.doStartTag();
        tag.doEndTag();
        assertEquals("bar", writer.toString());
    }
