	private static AssertAssignbleMatcher assertAssignable(final ResolvableType type, final ResolvableType... fromTypes) {
		return new AssertAssignbleMatcher() {
			@Override
			public void equalTo(boolean... values) {
				for (int i = 0; i < fromTypes.length; i++) {
					assertThat(stringDesc(type) + " isAssignableFrom " + stringDesc(fromTypes[i]),
							type.isAssignableFrom(fromTypes[i]), Matchers.equalTo(values[i]));
				}
			}
		};
	}
