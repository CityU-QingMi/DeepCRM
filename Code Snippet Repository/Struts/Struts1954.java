    public void testStreamResultParse2() throws Exception {
        ///////////////////
        result.setParse(true);
        // This time we dun use ${...}, so streamForImage will
        // be evaluated to the stack, which should reaturn an
        // InputStream object, cause there's such a property in
        // the action object itself.
        result.setInputName("streamForImage");
        result.setBufferSize(128);
        result.setContentLength(String.valueOf(contentLength));
        result.setContentDisposition("filename=\"logo.png\"");
        result.setContentType("image/jpeg");

        result.doExecute("helloworld", mai);

        assertEquals(String.valueOf(contentLength), result.getContentLength());
        assertEquals("image/jpeg", result.getContentType());
        assertEquals("streamForImage", result.getInputName());
        assertEquals(128, result.getBufferSize());
        assertEquals("filename=\"logo.png\"", result.getContentDisposition());

        assertEquals("image/jpeg", response.getContentType());
        assertEquals(contentLength, response.getContentLength());
        assertEquals("filename=\"logo.png\"", response.getHeader("Content-disposition"));
    }
