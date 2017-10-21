	@Test
	@SuppressWarnings({ "", "" })
	public void SPR10417() {
		List list1 = new ArrayList();
		list1.add("a");
		list1.add("b");
		list1.add("x");
		List list2 = new ArrayList();
		list2.add("c");
		list2.add("x");
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("list1", list1);
		context.setVariable("list2", list2);

		// #this should be the element from list1
		Expression ex = parser.parseExpression("#list1.?[#list2.contains(#this)]");
		Object result = ex.getValue(context);
		assertEquals("[x]", result.toString());

		// toString() should be called on the element from list1
		ex = parser.parseExpression("#list1.?[#list2.contains(toString())]");
		result = ex.getValue(context);
		assertEquals("[x]", result.toString());

		List list3 = new ArrayList();
		list3.add(1);
		list3.add(2);
		list3.add(3);
		list3.add(4);

		context = new StandardEvaluationContext();
		context.setVariable("list3", list3);
		ex = parser.parseExpression("#list3.?[#this > 2]");
		result = ex.getValue(context);
		assertEquals("[3, 4]", result.toString());

		ex = parser.parseExpression("#list3.?[#this >= T(java.lang.Math).abs(T(java.lang.Math).abs(#this))]");
		result = ex.getValue(context);
		assertEquals("[1, 2, 3, 4]", result.toString());
	}
