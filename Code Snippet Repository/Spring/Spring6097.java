	@Test
	public void test_binaryPlusWithNumberOperands() {
		ExpressionState expressionState = new ExpressionState(new StandardEvaluationContext());

		{
			RealLiteral n1 = new RealLiteral("123.00", -1, 123.0);
			RealLiteral n2 = new RealLiteral("456.00", -1, 456.0);
			OpPlus o = new OpPlus(-1, n1, n2);
			TypedValue value = o.getValueInternal(expressionState);

			assertEquals(Double.class, value.getTypeDescriptor().getObjectType());
			assertEquals(Double.class, value.getTypeDescriptor().getType());
			assertEquals(Double.valueOf(123.0 + 456.0), value.getValue());
		}

		{
			LongLiteral n1 = new LongLiteral("123", -1, 123L);
			LongLiteral n2 = new LongLiteral("456", -1, 456L);
			OpPlus o = new OpPlus(-1, n1, n2);
			TypedValue value = o.getValueInternal(expressionState);

			assertEquals(Long.class, value.getTypeDescriptor().getObjectType());
			assertEquals(Long.class, value.getTypeDescriptor().getType());
			assertEquals(Long.valueOf(123L + 456L), value.getValue());
		}

		{
			IntLiteral n1 = new IntLiteral("123", -1, 123);
			IntLiteral n2 = new IntLiteral("456", -1, 456);
			OpPlus o = new OpPlus(-1, n1, n2);
			TypedValue value = o.getValueInternal(expressionState);

			assertEquals(Integer.class, value.getTypeDescriptor().getObjectType());
			assertEquals(Integer.class, value.getTypeDescriptor().getType());
			assertEquals(Integer.valueOf(123 + 456), value.getValue());
		}
	}
