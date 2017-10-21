	@Test
	public void mixingItUp_propertyAccessIndexerOpLtTernaryRootNull() throws Exception {
		Payload payload = new Payload();

		expression = parser.parseExpression("DR[0].three");
		Object v = expression.getValue(payload);
		assertEquals("Lorg/springframework/expression/spel/SpelCompilationCoverageTests$Three",
				getAst().getExitDescriptor());

		Expression expression = parser.parseExpression("DR[0].three.four lt 0.1d?#root:null");
		v = expression.getValue(payload);

		SpelExpression sExpr = (SpelExpression) expression;
		Ternary ternary = (Ternary) sExpr.getAST();
		OpLT oplt = (OpLT) ternary.getChild(0);
		CompoundExpression cExpr = (CompoundExpression) oplt.getLeftOperand();
		String cExprExitDescriptor = cExpr.getExitDescriptor();
		assertEquals("D", cExprExitDescriptor);
		assertEquals("Z", oplt.getExitDescriptor());

		assertCanCompile(expression);
		Object vc = expression.getValue(payload);
		assertEquals(payload, v);
		assertEquals(payload,vc);
		payload.DR[0].three.four = 0.13d;
		vc = expression.getValue(payload);
		assertNull(vc);
	}
