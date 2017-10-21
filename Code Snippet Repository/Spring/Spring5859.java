	@Test
	public void testCoercionToCollectionOfPrimitive() throws Exception {

		class TestTarget {
			@SuppressWarnings("unused")
			public int sum(Collection<Integer> numbers) {
				int total = 0;
				for (int i : numbers) {
					total += i;
				}
				return total;
			}
		}

		StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

		TypeDescriptor collectionType = new TypeDescriptor(new MethodParameter(TestTarget.class.getDeclaredMethod(
				"sum", Collection.class), 0));
		// The type conversion is possible
		assertTrue(evaluationContext.getTypeConverter()
				.canConvert(TypeDescriptor.valueOf(String.class), collectionType));
		// ... and it can be done successfully
		assertEquals("[1, 2, 3, 4]", evaluationContext.getTypeConverter().convertValue("1,2,3,4", TypeDescriptor.valueOf(String.class), collectionType).toString());

		evaluationContext.setVariable("target", new TestTarget());

		// OK up to here, so the evaluation should be fine...
		// ... but this fails
		int result = (Integer) parser.parseExpression("#target.sum(#root)").getValue(evaluationContext, "1,2,3,4");
		assertEquals("Wrong result: " + result, 10, result);

	}
