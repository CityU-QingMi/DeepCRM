	public void testSaveResult() throws Exception {

		Object methodResult = "index";
		ActionConfig actionConfig = restActionInvocation.getProxy().getConfig();
		assertEquals("index", restActionInvocation.saveResult(actionConfig, methodResult));
    	
		setUp();
    	methodResult = new DefaultHttpHeaders("show");
    	assertEquals("show", restActionInvocation.saveResult(actionConfig, methodResult));
    	assertEquals(methodResult, restActionInvocation.httpHeaders);
    	
		setUp();
    	methodResult = new HttpHeaderResult(HttpServletResponse.SC_ACCEPTED);
    	assertEquals(null, restActionInvocation.saveResult(actionConfig, methodResult));
    	assertEquals(methodResult, restActionInvocation.createResult());

		setUp();
    	try {
    		methodResult = new Object();
    		restActionInvocation.saveResult(actionConfig, methodResult);

    		// ko
    		assertFalse(true);
    		
    	} catch (ConfigurationException c) {
    		// ok, object not allowed
    	}
	}
