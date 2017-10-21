    public void testCustomFormatLong() throws Exception {
        String format = "yyyy/MM/dd hh:mm:ss";
        Date date = new Date();
        String formatted = new SimpleDateFormat(format).format(date);
        // long
        context.put("myDate", date.getTime());

        tag.setName("myDate");
        tag.setNice(false);
        tag.setFormat(format);
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(formatted, writer.toString());
    }
