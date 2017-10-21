	public void testInvoke() throws Exception {
        
	    // Default index method return 'success'
	    ((MockActionProxy)restActionInvocation.getProxy()).setMethod("index");

	    // Define result 'success'
		ResultConfig resultConfig = new ResultConfig.Builder("success", 
			"org.apache.struts2.result.HttpHeaderResult")
	    	.addParam("status", "123").build();
	    ActionConfig actionConfig = new ActionConfig.Builder("org.apache.rest", 
				"RestAction", "org.apache.rest.RestAction")
	    	.addResultConfig(resultConfig)
	    	.build();
	    ((MockActionProxy)restActionInvocation.getProxy()).setConfig(actionConfig);

		request.setMethod("GET");
		
        restActionInvocation.setOgnlUtil(new OgnlUtil());
        restActionInvocation.invoke();

        assertEquals(123, response.getStatus());
    }
