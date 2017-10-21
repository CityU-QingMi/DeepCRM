    public void testApplyFullLocation() {
        DefaultHttpHeaders headers = new DefaultHttpHeaders()
                .setLocation("http://localhost/bar/44");
        mockRequest.setRequestURI("/foo/bar");

        headers.apply(mockRequest, mockResponse, new Object());
        assertEquals("http://localhost/bar/44", mockResponse.getHeader("Location"));
        assertEquals(SC_CREATED, mockResponse.getStatus());

    }
