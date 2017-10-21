	@Test
	public void mixingItUp_indexerOpEqTernary() throws Exception {
		Map<String, String> m = new HashMap<>();
		m.put("andy","778");

		expression = parse("['andy']==null?1:2");
		assertEquals(2, expression.getValue(m));
		assertCanCompile(expression);
		assertEquals(2, expression.getValue(m));
		m.remove("andy");
		assertEquals(1, expression.getValue(m));
	}
