	@Test
	public void SPR11348() {
		Collection<String> coll = new LinkedHashSet<>();
		coll.add("one");
		coll.add("two");
		coll = Collections.unmodifiableCollection(coll);

		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression("new java.util.ArrayList(#root)");
		Object value = expr.getValue(coll);
		assertTrue(value instanceof ArrayList);
		@SuppressWarnings("rawtypes")
		ArrayList list = (ArrayList) value;
		assertEquals("one", list.get(0));
		assertEquals("two", list.get(1));
	}
