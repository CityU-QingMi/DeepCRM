	@Test
	public void opEq_SPR14863() throws Exception {
		// Exercise the comparator invocation code that runs in
		// equalityCheck() (called from interpreted and compiled code)
		expression = parser.parseExpression("#aa==#bb");
		StandardEvaluationContext sec = new StandardEvaluationContext();
		Apple aa = new Apple(1);
		Apple bb = new Apple(2);
		sec.setVariable("aa",aa);
		sec.setVariable("bb",bb);
		boolean b = expression.getValue(sec, Boolean.class);
		// Verify what the expression caused aa to be compared to
		assertEquals(bb,aa.gotComparedTo);
		assertFalse(b);
		bb.setValue(1);
		b = expression.getValue(sec, Boolean.class);
		assertEquals(bb,aa.gotComparedTo);
		assertTrue(b);

		assertCanCompile(expression);

		// Similar test with compiled expression
		aa = new Apple(99);
		bb = new Apple(100);
		sec.setVariable("aa",aa);
		sec.setVariable("bb",bb);
		b = expression.getValue(sec, Boolean.class);
		assertFalse(b);
		assertEquals(bb,aa.gotComparedTo);
		bb.setValue(99);
		b = expression.getValue(sec, Boolean.class);
		assertTrue(b);
		assertEquals(bb,aa.gotComparedTo);


		List<String> ls = new ArrayList<String>();
		ls.add(new String("foo"));
		StandardEvaluationContext context = new StandardEvaluationContext(ls);
		expression = parse("get(0) == 'foo'");
		assertTrue(expression.getValue(context, Boolean.class));
		assertCanCompile(expression);
		assertTrue(expression.getValue(context, Boolean.class));

		ls.remove(0);
		ls.add("goo");
		assertFalse(expression.getValue(context, Boolean.class));
	}
