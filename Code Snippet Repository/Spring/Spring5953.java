	@Test
	public void testSetGenericMapElementRequiresCoercion() throws Exception {
		StandardEvaluationContext eContext = TestScenarioCreator.getTestEvaluationContext();
		Expression e = parse("mapOfStringToBoolean[42]");
		assertNull(e.getValue(eContext));

		// Key should be coerced to string representation of 42
		e.setValue(eContext, "true");

		// All keys should be strings
		Set<?> ks = parse("mapOfStringToBoolean.keySet()").getValue(eContext, Set.class);
		for (Object o: ks) {
			assertEquals(String.class,o.getClass());
		}

		// All values should be booleans
		Collection<?> vs = parse("mapOfStringToBoolean.values()").getValue(eContext, Collection.class);
		for (Object o: vs) {
			assertEquals(Boolean.class, o.getClass());
		}

		// One final test check coercion on the key for a map lookup
		Object o = e.getValue(eContext);
		assertEquals(Boolean.TRUE,o);
	}
