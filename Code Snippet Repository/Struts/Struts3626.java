    public void testApply() {
        Date now = new Date();
        DefaultHttpHeaders headers = new DefaultHttpHeaders()
                .lastModified(now)
                .withStatus(SC_OK)
                .setLocationId("44")
                .withETag("asdf");
        mockRequest.setRequestURI("/foo/bar.xhtml");

        headers.apply(mockRequest, mockResponse, new Object());

        assertEquals(SC_CREATED, mockResponse.getStatus());
        assertEquals("http://localhost/foo/bar/44.xhtml", mockResponse.getHeader("Location"));
        assertEquals("asdf", mockResponse.getHeader("ETag"));
        assertEquals(String.valueOf(now.getTime()), mockResponse.getHeader("Last-Modified"));

    }
