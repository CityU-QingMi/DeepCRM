	@Test
	public void repeatedCompilation() throws Exception {
		// Verifying that after a number of compilations, the classloaders
		// used to load the compiled expressions are discarded/replaced.
		// See SpelCompiler.loadClass()
		Field f = SpelExpression.class.getDeclaredField("compiledAst");
		Set<Object> classloadersUsed = new HashSet<>();
		for (int i = 0; i < 1500; i++) {  // 1500 is greater than SpelCompiler.CLASSES_DEFINED_LIMIT
			expression = parser.parseExpression("4 + 5");
			assertEquals(9, (int) expression.getValue(Integer.class));
			assertCanCompile(expression);
			f.setAccessible(true);
			CompiledExpression cEx = (CompiledExpression) f.get(expression);
			classloadersUsed.add(cEx.getClass().getClassLoader());
			assertEquals(9, (int) expression.getValue(Integer.class));
		}
		assertTrue(classloadersUsed.size() > 1);
	}
