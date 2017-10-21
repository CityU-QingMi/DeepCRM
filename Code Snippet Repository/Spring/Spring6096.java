	@Test
	public void test_unaryPlusWithNumberOperand() {
		ExpressionState expressionState = new ExpressionState(new StandardEvaluationContext());

		{
			RealLiteral realLiteral = new RealLiteral("123.00", -1, 123.0);
			OpPlus o = new OpPlus(-1, realLiteral);
			TypedValue value = o.getValueInternal(expressionState);

			assertEquals(Double.class, value.getTypeDescriptor().getObjectType());
			assertEquals(Double.class, value.getTypeDescriptor().getType());
			assertEquals(realLiteral.getLiteralValue().getValue(), value.getValue());
		}

		{
			IntLiteral intLiteral = new IntLiteral("123", -1, 123);
			OpPlus o = new OpPlus(-1, intLiteral);
			TypedValue value = o.getValueInternal(expressionState);

			assertEquals(Integer.class, value.getTypeDescriptor().getObjectType());
			assertEquals(Integer.class, value.getTypeDescriptor().getType());
			assertEquals(intLiteral.getLiteralValue().getValue(), value.getValue());
		}

		{
			LongLiteral longLiteral = new LongLiteral("123", -1, 123L);
			OpPlus o = new OpPlus(-1, longLiteral);
			TypedValue value = o.getValueInternal(expressionState);

			assertEquals(Long.class, value.getTypeDescriptor().getObjectType());
			assertEquals(Long.class, value.getTypeDescriptor().getType());
			assertEquals(longLiteral.getLiteralValue().getValue(), value.getValue());
		}
	}
