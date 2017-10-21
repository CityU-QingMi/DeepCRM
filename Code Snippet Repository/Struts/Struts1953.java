    public void testStreamResultParse1() throws Exception {
        ///////////////////
        result.setParse(true);
        // ${...} conditionalParse of Result, returns String,
        // which gets evaluated to the stack, that's how it works.
        // We use ${streamForImageAsString} that returns "streamForImage"
        // which is a property that returns an InputStream object.
        result.setInputName("${streamForImageAsString}");
        result.setBufferSize(128);
        result.setContentLength(String.valueOf(contentLength));
        result.setContentDisposition("filename=\"logo.png\"");
        result.setContentType("image/jpeg");

        result.doExecute("helloworld", mai);

        assertEquals(String.valueOf(contentLength), result.getContentLength());
        assertEquals("image/jpeg", result.getContentType());
        assertEquals("${streamForImageAsString}", result.getInputName());
        assertEquals(128, result.getBufferSize());
        assertEquals("filename=\"logo.png\"", result.getContentDisposition());

        assertEquals("image/jpeg", response.getContentType());
        assertEquals(contentLength, response.getContentLength());
        assertEquals("filename=\"logo.png\"", response.getHeader("Content-disposition"));
    }
