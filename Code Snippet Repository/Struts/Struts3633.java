    public void testApplyOptions() {
    	
    	String methods = "OPTIONS, GET, POST, PUT";
    	String allow = "Allow";
    	
    	mockResponse.addHeader(allow, methods);
    	
    	DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
    	httpHeaders.apply(mockRequest, mockResponse, this);
    	httpHeaders.disableCaching().withStatus(SC_OK);
    	
        assertEquals(methods, mockResponse.getHeader(allow));
        assertEquals(SC_OK, mockResponse.getStatus());

    }
