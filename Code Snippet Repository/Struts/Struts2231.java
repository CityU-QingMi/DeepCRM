    public void testCustomFormatWithTimezoneAsExpression() throws Exception {
        String format = "yyyy/MM/dd hh:mm:ss";
        Date now = Calendar.getInstance(TimeZone.getTimeZone("UTC+2")).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC+2"));
        String formatted = sdf.format(now);
        context.put("myDate", now);
        context.put("myTimezone", "UTC+2");

        tag.setName("myDate");
        tag.setNice(false);
        tag.setFormat(format);
        tag.setTimezone("myTimezone");
        tag.doStartTag();
        tag.doEndTag();
        assertEquals(formatted, writer.toString());
    }
