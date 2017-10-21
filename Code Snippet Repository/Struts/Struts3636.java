	public void testSelectTarget() throws Exception {
		
		// Exception
		Exception e = new Exception();
		restActionInvocation.getStack().set("exception", e);
		restActionInvocation.selectTarget();
		assertEquals(e, restActionInvocation.target);

		// Error messages
		setUp();
		String actionMessage = "Error!";
		RestActionSupport action = (RestActionSupport)restActionInvocation.getAction();
		action.addActionError(actionMessage);
		Map<String, Object> errors = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add(actionMessage);
    	errors.put("actionErrors", list);
    	restActionInvocation.selectTarget();
		assertEquals(errors, restActionInvocation.target);
		
    	// Model with get and no content in post, put, delete
    	setUp();
		RestAction restAction = (RestAction)restActionInvocation.getAction();
		List<String> model = new ArrayList<String>();
		model.add("Item");
		restAction.model = model;
		request.setMethod("GET");
		restActionInvocation.selectTarget();
		assertEquals(model, restActionInvocation.target);
		request.setMethod("POST");
		restActionInvocation.selectTarget();
		assertEquals(null, restActionInvocation.target);
		request.setMethod("PUT");
		restActionInvocation.selectTarget();
		assertEquals(null, restActionInvocation.target);
		request.setMethod("DELETE");
		restActionInvocation.selectTarget();
		assertEquals(null, restActionInvocation.target);

        // disable content restriction to GET only
        model = new ArrayList<String>();
        model.add("Item1");
        restAction.model = model;

        request.setMethod("POST");
        restActionInvocation.setRestrictToGet("false");
        restActionInvocation.selectTarget();
        assertEquals(model, restActionInvocation.target);
        assertEquals(model.get(0), "Item1");
    }
