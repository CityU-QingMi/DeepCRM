	@Test
	public void compoundExpression() throws Exception {
		Payload payload = new Payload();
		expression = parser.parseExpression("DR[0]");
		assertEquals("instanceof Two", expression.getValue(payload).toString());
		assertCanCompile(expression);
		assertEquals("instanceof Two", expression.getValue(payload).toString());
		ast = getAst();
		assertEquals("Lorg/springframework/expression/spel/SpelCompilationCoverageTests$Two", ast.getExitDescriptor());

		expression = parser.parseExpression("holder.three");
		assertEquals("org.springframework.expression.spel.SpelCompilationCoverageTests$Three", expression.getValue(payload).getClass().getName());
		assertCanCompile(expression);
		assertEquals("org.springframework.expression.spel.SpelCompilationCoverageTests$Three", expression.getValue(payload).getClass().getName());
		ast = getAst();
		assertEquals("Lorg/springframework/expression/spel/SpelCompilationCoverageTests$Three", ast.getExitDescriptor());

		expression = parser.parseExpression("DR[0]");
		assertEquals("org.springframework.expression.spel.SpelCompilationCoverageTests$Two", expression.getValue(payload).getClass().getName());
		assertCanCompile(expression);
		assertEquals("org.springframework.expression.spel.SpelCompilationCoverageTests$Two", expression.getValue(payload).getClass().getName());
		assertEquals("Lorg/springframework/expression/spel/SpelCompilationCoverageTests$Two", getAst().getExitDescriptor());

		expression = parser.parseExpression("DR[0].three");
		assertEquals("org.springframework.expression.spel.SpelCompilationCoverageTests$Three", expression.getValue(payload).getClass().getName());
		assertCanCompile(expression);
		assertEquals("org.springframework.expression.spel.SpelCompilationCoverageTests$Three", expression.getValue(payload).getClass().getName());
		ast = getAst();
		assertEquals("Lorg/springframework/expression/spel/SpelCompilationCoverageTests$Three", ast.getExitDescriptor());

		expression = parser.parseExpression("DR[0].three.four");
		assertEquals(0.04d, expression.getValue(payload));
		assertCanCompile(expression);
		assertEquals(0.04d, expression.getValue(payload));
		assertEquals("D", getAst().getExitDescriptor());
	}
