    public void testCustomFormatAndComponent() throws Exception {
        String format = "yyyy/MM/dd hh:mm:ss";
        Date now = new Date();
        String formatted = new SimpleDateFormat(format).format(now);
        context.put("myDate", now);

        tag.setName("myDate");
        tag.setFormat(format);
        tag.setNice(false);

        tag.doStartTag();

        // component test must be done between start and end tag
        org.apache.struts2.components.Date component = (org.apache.struts2.components.Date) tag.getComponent();
        assertEquals("myDate", component.getName());
        assertEquals(format, component.getFormat());
        assertEquals(false, component.isNice());

        tag.doEndTag();

        assertEquals(formatted, writer.toString());
    }
