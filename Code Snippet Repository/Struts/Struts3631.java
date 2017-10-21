    public void testConditionalGetForLastModifiedAndETagButNoCache() {
        Date now = new Date();
        DefaultHttpHeaders headers = new DefaultHttpHeaders()
                .lastModified(now)
                .withETag("asdf")
                .disableCaching();
        mockRequest.addHeader("If-None-Match", "asdf");
        mockRequest.addHeader("If-Modified-Since", String.valueOf(now.getTime()));
        headers.apply(mockRequest, mockResponse, new Object());

        assertEquals(SC_OK, mockResponse.getStatus());
    }
