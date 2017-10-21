    public void testConditionalGetForLastModifiedAndETag() {
        Date now = new Date();
        DefaultHttpHeaders headers = new DefaultHttpHeaders()
                .lastModified(now)
                .withETag("asdf");
        mockRequest.addHeader("If-None-Match", "asdf");
        mockRequest.addHeader("If-Modified-Since", getGMTDateFormat().format(now));
        headers.apply(mockRequest, mockResponse, new Object());

        assertEquals(SC_NOT_MODIFIED, mockResponse.getStatus());
    }
