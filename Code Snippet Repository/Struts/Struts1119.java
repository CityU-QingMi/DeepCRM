	public void testInheritedResultTypesParams() throws Exception {
		ConfigurationProvider configurationProvider = buildConfigurationProvider("com/opensymphony/xwork2/config/providers/xwork-test-result-types.xml");
		
		PackageConfig packageConfig = configuration.getPackageConfig("xworkResultTypesTestPackage2");
		Map actionConfigMap = packageConfig.getActionConfigs();
		
		
		ActionConfig action1ActionConfig = (ActionConfig) actionConfigMap.get("action1");
		ActionConfig action2ActionConfig = (ActionConfig) actionConfigMap.get("action2");
		
		ResultConfig action1Result = (ResultConfig) action1ActionConfig.getResults().get("success");
		ResultConfig action2Result = (ResultConfig) action2ActionConfig.getResults().get("success");
		
		assertEquals(action1Result.getName(), "success");
		assertEquals(action1Result.getClassName(), "com.opensymphony.xwork2.mock.MockResult");
		assertEquals(action1Result.getName(), "success");
		assertEquals(action1Result.getClassName(), "com.opensymphony.xwork2.mock.MockResult");
		
		Map action1ResultMap = action1Result.getParams();
		Map action2ResultMap = action2Result.getParams();
		
		assertEquals(action1ResultMap.size(), 5);
		assertTrue(action1ResultMap.containsKey("param1"));
		assertTrue(action1ResultMap.containsKey("param2"));
		assertTrue(action1ResultMap.containsKey("param3"));
		assertTrue(action1ResultMap.containsKey("param10"));
		assertTrue(action1ResultMap.containsKey("param11"));
		assertFalse(action1ResultMap.containsKey("param12"));
		assertEquals(action1ResultMap.get("param1"), "newValue1");
		assertEquals(action1ResultMap.get("param2"), "value2");
		assertEquals(action1ResultMap.get("param3"), "newValue3");
		assertEquals(action1ResultMap.get("param10"), "value10");
		assertEquals(action1ResultMap.get("param11"), "value11");
		
		assertEquals(action2ResultMap.size(), 3);
		assertTrue(action2ResultMap.containsKey("paramA"));
		assertTrue(action2ResultMap.containsKey("paramB"));
		assertTrue(action2ResultMap.containsKey("paramZ"));
		assertFalse(action2ResultMap.containsKey("paramY"));
		assertEquals(action2ResultMap.get("paramA"), "valueA");
		assertEquals(action2ResultMap.get("paramB"), "newValueB");
		assertEquals(action2ResultMap.get("paramZ"), "valueZ");
		
		
	}
