	public void testInterception1() throws Exception {
		contextMap.put(ActionContext.PARAMETERS, HttpParameters.create(new LinkedHashMap<String, Object>() {
			{
				put("param1", new String[]{"paramValue1"});
				put("param2", new String[]{"paramValue2"});
				put("param3", new String[]{"paramValue3"});
				put("param", new String[]{"paramValue"});
			}
		}).build());
		
		replay(actionInvocation);
		
		ParameterRemoverInterceptor interceptor = new ParameterRemoverInterceptor();
		interceptor.setParamNames("param1,param2");
		interceptor.setParamValues("paramValue1,paramValue2");
		interceptor.intercept(actionInvocation);
		
		HttpParameters params = (HttpParameters) contextMap.get(ActionContext.PARAMETERS);
		assertEquals(params.keySet().size(), 2);
		assertTrue(params.contains("param3"));
		assertTrue(params.contains("param"));
		assertEquals(params.get("param3").getValue(), "paramValue3");
		assertEquals(params.get("param").getValue(), "paramValue");
		
		verify(actionInvocation);
	}
