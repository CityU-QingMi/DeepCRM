    public void testCustomFormatWithTimezone() throws Exception {
        String format = "yyyy/MM/dd hh:mm:ss";
        Date now = Calendar.getInstance(TimeZone.getTimeZone("UTC+1")).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+1"));
        String formatted = sdf.format(now);
        context.put("myDate", now);

        tag.setName("myDate");
        tag.setNice(false);
        tag.setFormat(format);
        tag.setTimezone("UTC+1");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(formatted, writer.toString());
    }
