    @Test
    public void test3_3()
    {
        try
        {
            HttpFields fields=new HttpFields();

            fields.put("D1","Sun, 6 Nov 1994 08:49:37 GMT");
            fields.put("D2","Sunday, 6-Nov-94 08:49:37 GMT");
            fields.put("D3","Sun Nov  6 08:49:37 1994");
            Date d1=new Date(fields.getDateField("D1"));
            Date d2=new Date(fields.getDateField("D2"));
            Date d3=new Date(fields.getDateField("D3"));

            assertEquals("3.3.1 RFC 822 RFC 850",d2,d1);
            assertEquals("3.3.1 RFC 850 ANSI C",d3,d2);

            fields.putDateField("Date",d1.getTime());
            assertEquals("3.3.1 RFC 822 preferred","Sun, 06 Nov 1994 08:49:37 GMT",fields.get("Date"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            assertTrue(false);
        }
    }
