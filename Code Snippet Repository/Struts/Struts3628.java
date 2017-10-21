    public void testAutoETag() {
        DefaultHttpHeaders headers = new DefaultHttpHeaders();
        headers.apply(mockRequest, mockResponse, new Object() {
            @Override
            public int hashCode() {
                return 123;
            }
        });

        assertEquals("123", mockResponse.getHeader("ETag"));
    }
