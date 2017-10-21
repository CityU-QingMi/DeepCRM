    public void testConditionalGetForJustETagNotOK() {
        DefaultHttpHeaders headers = new DefaultHttpHeaders()
                .withETag("asdf")
                .withStatus(SC_BAD_REQUEST);
        mockRequest.addHeader("If-None-Match", "asdf");
        headers.apply(mockRequest, mockResponse, new Object());

        assertEquals(SC_BAD_REQUEST, mockResponse.getStatus());
        assertEquals("asdf", mockResponse.getHeader("ETag"));
    }
