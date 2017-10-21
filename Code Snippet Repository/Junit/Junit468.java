	@Test
	void anonymousClassEvaluatesToFalse() {

		Object object = new Object() {
			@Override
			public String toString() {
				return "";
			}
		};

		assertFalse(isPotentialTestContainer.test(object.getClass()));
	}
