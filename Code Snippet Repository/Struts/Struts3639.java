	public void testNoResult() throws Exception {
		
		RestAction restAction = (RestAction)restActionInvocation.getAction();
		List<String> model = new ArrayList<String>();
		model.add("Item");
		restAction.model = model;
		request.setMethod("GET");
		restActionInvocation.setResultCode("index");

		try {
			restActionInvocation.processResult();

    		// ko
    		assertFalse(true);
    		
    	} catch (ConfigurationException c) {
    		// ok, no result
    	}

	}
