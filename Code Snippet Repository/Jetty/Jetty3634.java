    @Test
    public void testInferredCharset() throws Exception
    {
        // Inferred from encoding.properties
        Response response = getResponse();

        assertEquals(null, response.getContentType());

        response.setHeader("Content-Type", "application/xhtml+xml");
        assertEquals("application/xhtml+xml", response.getContentType());
        response.getWriter();
        assertEquals("application/xhtml+xml;charset=utf-8", response.getContentType());
        assertEquals("utf-8", response.getCharacterEncoding());
    }
