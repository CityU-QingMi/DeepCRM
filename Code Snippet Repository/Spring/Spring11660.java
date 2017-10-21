	private void assertConditions(ProducesRequestCondition condition, String... expected) {
		Collection<ProducesRequestCondition.ProduceMediaTypeExpression> expressions = condition.getContent();
		assertEquals("Invalid number of conditions", expressions.size(), expected.length);
		for (String s : expected) {
			boolean found = false;
			for (ProducesRequestCondition.ProduceMediaTypeExpression expr : expressions) {
				String conditionMediaType = expr.getMediaType().toString();
				if (conditionMediaType.equals(s)) {
					found = true;
					break;

				}
			}
			if (!found) {
				fail("Condition [" + s + "] not found");
			}
		}
	}
