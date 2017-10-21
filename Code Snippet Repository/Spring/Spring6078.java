	@Test
	@SuppressWarnings({ "", "" })
	public void SPR10417_maps() {
		Map map1 = new HashMap();
		map1.put("A", 65);
		map1.put("B", 66);
		map1.put("X", 66);
		Map map2 = new HashMap();
		map2.put("X", 66);

		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("map1", map1);
		context.setVariable("map2", map2);

		// #this should be the element from list1
		Expression ex = parser.parseExpression("#map1.?[#map2.containsKey(#this.getKey())]");
		Object result = ex.getValue(context);
		assertEquals("{X=66}", result.toString());

		ex = parser.parseExpression("#map1.?[#map2.containsKey(key)]");
		result = ex.getValue(context);
		assertEquals("{X=66}", result.toString());
	}
