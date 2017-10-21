	public void testPlainResultTypesParams() throws Exception {
		ConfigurationProvider configurationProvider = buildConfigurationProvider("com/opensymphony/xwork2/config/providers/xwork-test-result-types.xml");
		
		PackageConfig packageConfig = configuration.getPackageConfig("xworkResultTypesTestPackage1");
		Map resultTypesConfigMap = packageConfig.getResultTypeConfigs();
		
		assertEquals(resultTypesConfigMap.size(), 2);
		assertTrue(resultTypesConfigMap.containsKey("result1"));
		assertTrue(resultTypesConfigMap.containsKey("result2"));
		assertFalse(resultTypesConfigMap.containsKey("result3"));
		
		ResultTypeConfig result1ResultTypeConfig = (ResultTypeConfig) resultTypesConfigMap.get("result1");
		Map result1ParamsMap = result1ResultTypeConfig.getParams();
		ResultTypeConfig result2ResultTypeConfig = (ResultTypeConfig) resultTypesConfigMap.get("result2");
		Map result2ParamsMap = result2ResultTypeConfig.getParams();
		
		assertEquals(result1ResultTypeConfig.getName(), "result1");
		assertEquals(result1ResultTypeConfig.getClassName(), MockResult.class.getName());
		assertEquals(result2ResultTypeConfig.getName(), "result2");
		assertEquals(result2ResultTypeConfig.getClassName(), MockResult.class.getName());
		assertEquals(result1ParamsMap.size(), 3);
		assertEquals(result2ParamsMap.size(), 2);
		assertTrue(result1ParamsMap.containsKey("param1"));
		assertTrue(result1ParamsMap.containsKey("param2"));
		assertTrue(result1ParamsMap.containsKey("param3"));
		assertFalse(result1ParamsMap.containsKey("param4"));
		assertTrue(result2ParamsMap.containsKey("paramA"));
		assertTrue(result2ParamsMap.containsKey("paramB"));
		assertFalse(result2ParamsMap.containsKey("paramC"));
		assertEquals(result1ParamsMap.get("param1"), "value1");
		assertEquals(result1ParamsMap.get("param2"), "value2");
		assertEquals(result1ParamsMap.get("param3"), "value3");
		assertEquals(result2ParamsMap.get("paramA"), "valueA");
		assertEquals(result2ParamsMap.get("paramB"), "valueB");
	}
