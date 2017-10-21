    public void testConditionalGetForLastModifiedAndETagWithBadETag() {
        Date now = new Date();
        DefaultHttpHeaders headers = new DefaultHttpHeaders()
                .lastModified(now)
                .withETag("fdsa");
        mockRequest.addHeader("If-None-Match", "asdfds");
        mockRequest.addHeader("If-Modified-Since", String.valueOf(now.getTime()));
        headers.apply(mockRequest, mockResponse, new Object());

        assertEquals(SC_OK, mockResponse.getStatus());
    }
