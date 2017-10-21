    public void testCustomFormatCalendar() throws Exception {
        String format = "yyyy/MM/dd hh:mm:ss";
        Calendar calendar = Calendar.getInstance();
        String formatted = new SimpleDateFormat(format).format(calendar.getTime());
        context.put("myDate", calendar);

        tag.setName("myDate");
        tag.setNice(false);
        tag.setFormat(format);
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(formatted, writer.toString());
    }
