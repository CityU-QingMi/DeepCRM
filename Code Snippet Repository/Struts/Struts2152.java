    public void testMessageFormatWorks() throws Exception {
        String key = "messageFormatKey";
        String pattern = "Params are {0} {1} {2}";
        Object param1 = new Integer(12);
        Object param2 = new Date();
        Object param3 = "StringVal";
        List params = new ArrayList();
        params.add(param1);
        params.add(param2);
        params.add(param3);

        MessageFormat format = new MessageFormat(pattern, ActionContext.getContext().getLocale());
        String expected = format.format(params.toArray());

        tag.setName(key);
        tag.doStartTag();
        ((Text) tag.component).addParameter(param1);
        ((Text) tag.component).addParameter(param2);
        ((Text) tag.component).addParameter(param3);
        tag.doEndTag();
        assertEquals(expected, writer.toString());
    }
