	@Test
	public void SPR9735() {
		Item item = new Item();
		item.setName("parent");

		Item item1 = new Item();
		item1.setName("child1");

		Item item2 = new Item();
		item2.setName("child2");

		item.add(item1);
		item.add(item2);

		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		Expression exp = parser.parseExpression("#item[0].name");
		context.setVariable("item", item);

		assertEquals("child1", exp.getValue(context));
	}
