    public void testDefaultFormat() throws Exception {
        Date now = new Date();
        String formatted = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,
                ActionContext.getContext().getLocale()).format(now);

        context.put("myDate", now);
        tag.setName("myDate");
        tag.setNice(false);
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(formatted, writer.toString());
    }
