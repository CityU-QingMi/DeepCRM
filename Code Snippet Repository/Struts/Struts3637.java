	public void testResultNotModified() throws Exception {

		request.addHeader("If-None-Match", "123");
		request.setMethod("GET");

		RestAction restAction = (RestAction)restActionInvocation.getAction();
		List<String> model = new ArrayList<String>() {
			@Override
			public int hashCode() {
				return 123;
			}
		};
		model.add("Item");
		restAction.model = model;
		
		restActionInvocation.processResult();
		assertEquals(SC_NOT_MODIFIED, response.getStatus());
        
    }
