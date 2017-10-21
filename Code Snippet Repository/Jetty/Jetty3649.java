    @Test
    public void testStrangeContentType() throws Exception
    {
        Response response = getResponse();

        assertEquals(null, response.getContentType());

        response.recycle();
        response.setContentType("text/html;charset=utf-8;charset=UTF-8");
        response.getWriter();
        assertEquals("text/html;charset=utf-8;charset=UTF-8",response.getContentType());
        assertEquals("utf-8",response.getCharacterEncoding().toLowerCase(Locale.ENGLISH));
    }
