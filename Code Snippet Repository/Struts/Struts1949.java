    public void testStreamResultDefault() throws Exception {
        result.setInputName("streamForImage");

        result.doExecute("helloworld", mai);

        assertEquals(contentLength, response.getContentLength());
        assertEquals("text/plain", response.getContentType());
        assertEquals("streamForImage", result.getInputName());
        assertEquals(1024, result.getBufferSize()); // 1024 is default
        assertEquals("inline", result.getContentDisposition());

        assertEquals("text/plain", response.getContentType());
        assertEquals(contentLength, response.getContentLength());
        assertEquals("inline", response.getHeader("Content-disposition"));
    }
