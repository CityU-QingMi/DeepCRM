	@Test
	public void testGetValue(){
		Map<String,String> props1 = new HashMap<>();
		props1.put("key1", "value1");
		props1.put("key2", "value2");
		props1.put("key3", "value3");

		Object bean = new TestBean("name1", new TestBean("name2", null, "Description 2", 15, props1), "description 1", 6, props1);

		ExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression("testBean.properties['key2']");
		assertEquals("value2", expr.getValue(bean));
	}
