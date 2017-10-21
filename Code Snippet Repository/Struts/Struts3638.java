	public void testDefaultErrorResult() throws Exception {
		
		// Exception
		Exception e = new Exception();
		restActionInvocation.getStack().set("exception", e);
		request.setMethod("GET");

		RestAction restAction = (RestAction)restActionInvocation.getAction();
		List<String> model = new ArrayList<String>();
		model.add("Item");
		restAction.model = model;
		
		restActionInvocation.setDefaultErrorResultName("default-error");
		ResultConfig resultConfig = new ResultConfig.Builder("default-error", 
			"org.apache.struts2.result.HttpHeaderResult")
	    	.addParam("status", "123").build();
	    ActionConfig actionConfig = new ActionConfig.Builder("org.apache.rest", 
				"RestAction", "org.apache.rest.RestAction")
	    	.addResultConfig(resultConfig)
	    	.build();
	    ((MockActionProxy)restActionInvocation.getProxy()).setConfig(actionConfig);
		
		restActionInvocation.processResult();
		assertEquals(123, response.getStatus());
		
	}
