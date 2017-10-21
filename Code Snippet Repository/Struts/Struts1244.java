	public void testInterception3() throws Exception {
		contextMap.put(ActionContext.PARAMETERS, HttpParameters.create(new LinkedHashMap<String, Object>() {
			{
				put("param1", new String[] { "paramValueOne" });
				put("param2", new String[] { "paramValueTwo" });
			}
		}).build());
		
		replay(actionInvocation);
		
		ParameterRemoverInterceptor interceptor = new ParameterRemoverInterceptor();
		interceptor.setParamNames("param1,param2");
		interceptor.setParamValues("paramValue1,paramValue2");
		interceptor.intercept(actionInvocation);
		
		HttpParameters params = (HttpParameters) contextMap.get(ActionContext.PARAMETERS);
		assertEquals(params.keySet().size(), 2);
		assertTrue(params.contains("param1"));
		assertTrue(params.contains("param2"));
		assertEquals(params.get("param1").getValue(), "paramValueOne");
		assertEquals(params.get("param2").getValue(), "paramValueTwo");
		
		verify(actionInvocation);
	}
