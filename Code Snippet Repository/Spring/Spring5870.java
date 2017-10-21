	@SuppressWarnings("")
	@Test
	public void testListsOfMap() {
		listOfMapsNotGeneric = new ArrayList();
		Map map = new HashMap();
		map.put("fruit", "apple");
		listOfMapsNotGeneric.add(map);
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("listOfMapsNotGeneric[0]['fruit']");
		assertEquals("apple", expression.getValue(this, String.class));
	}
