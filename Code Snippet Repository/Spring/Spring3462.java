	@Test
	public void mapAccessorCompilable() {
		Map<String, Object> testMap = getSimpleTestMap();
		StandardEvaluationContext sec = new StandardEvaluationContext();
		sec.addPropertyAccessor(new MapAccessor());
		SpelExpressionParser sep = new SpelExpressionParser();

		// basic
		Expression ex = sep.parseExpression("foo");
		assertEquals("bar",ex.getValue(sec,testMap));
		assertTrue(SpelCompiler.compile(ex));
		assertEquals("bar",ex.getValue(sec,testMap));

		// compound expression
		ex = sep.parseExpression("foo.toUpperCase()");
		assertEquals("BAR",ex.getValue(sec,testMap));
		assertTrue(SpelCompiler.compile(ex));
		assertEquals("BAR",ex.getValue(sec,testMap));

		// nested map
		Map<String,Map<String,Object>> nestedMap = getNestedTestMap();
		ex = sep.parseExpression("aaa.foo.toUpperCase()");
		assertEquals("BAR",ex.getValue(sec,nestedMap));
		assertTrue(SpelCompiler.compile(ex));
		assertEquals("BAR",ex.getValue(sec,nestedMap));

		// avoiding inserting checkcast because first part of expression returns a Map
		ex = sep.parseExpression("getMap().foo");
		MapGetter mapGetter = new MapGetter();
		assertEquals("bar",ex.getValue(sec,mapGetter));
		assertTrue(SpelCompiler.compile(ex));
		assertEquals("bar",ex.getValue(sec,mapGetter));
	}
