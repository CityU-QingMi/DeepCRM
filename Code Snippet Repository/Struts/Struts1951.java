    public void testStreamResultWithCharSet2() throws Exception {
        result.setParse(true);
        result.setInputName("streamForImage");
        result.setContentCharSet("${contentCharSetMethod}");

        result.doExecute("helloworld", mai);

        assertEquals(contentLength, response.getContentLength());
        assertEquals("text/plain", result.getContentType());
        assertEquals("text/plain;charset=UTF-8", response.getContentType());
        assertEquals("streamForImage", result.getInputName());
        assertEquals(1024, result.getBufferSize()); // 1024 is default
        assertEquals("inline", result.getContentDisposition());
        assertEquals("text/plain;charset=UTF-8", response.getContentType());
        assertEquals(contentLength, response.getContentLength());
        assertEquals("inline", response.getHeader("Content-disposition"));
    }
