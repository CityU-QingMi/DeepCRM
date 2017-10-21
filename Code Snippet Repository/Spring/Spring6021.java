	@Test
	public void testAssignment() throws Exception {
		Inventor inventor = new Inventor();
		StandardEvaluationContext inventorContext = new StandardEvaluationContext();
		inventorContext.setRootObject(inventor);

		parser.parseExpression("foo").setValue(inventorContext, "Alexander Seovic2");

		assertEquals("Alexander Seovic2",parser.parseExpression("foo").getValue(inventorContext,String.class));
		// alternatively

		String aleks = parser.parseExpression("foo = 'Alexandar Seovic'").getValue(inventorContext, String.class);
		assertEquals("Alexandar Seovic",parser.parseExpression("foo").getValue(inventorContext,String.class));
		assertEquals("Alexandar Seovic",aleks);
	}
