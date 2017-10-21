    public void testStreamResultWithCharSet() throws Exception {
        result.setInputName("streamForImage");
        result.setContentCharSet("ISO-8859-1");
        result.doExecute("helloworld", mai);

        assertEquals(contentLength, response.getContentLength());
        assertEquals("text/plain", result.getContentType());
        assertEquals("streamForImage", result.getInputName());
        assertEquals(1024, result.getBufferSize()); // 1024 is default
        assertEquals("inline", result.getContentDisposition());
        assertEquals("text/plain;charset=ISO-8859-1", response.getContentType());
        assertEquals(contentLength, response.getContentLength());
        assertEquals("inline", response.getHeader("Content-disposition"));
    }
