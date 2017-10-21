    @Test
    public void testAssumedCharset() throws Exception
    {
        Response response = getResponse();

        // Assumed from known types
        assertEquals(null, response.getContentType());
        response.setHeader("Content-Type", "text/json");
        assertEquals("text/json", response.getContentType());
        response.getWriter();
        assertEquals("text/json", response.getContentType());
        assertEquals("utf-8", response.getCharacterEncoding());

        response.recycle();

        // Assumed from encoding.properties
        assertEquals(null, response.getContentType());
        response.setHeader("Content-Type", "application/vnd.api+json");
        assertEquals("application/vnd.api+json", response.getContentType());
        response.getWriter();
        assertEquals("application/vnd.api+json", response.getContentType());
        assertEquals("utf-8", response.getCharacterEncoding());
    }
