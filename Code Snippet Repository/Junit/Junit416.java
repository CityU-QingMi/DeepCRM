		@Override
		public boolean supportsTestTemplate(ExtensionContext context) {
			// @formatter:off
			return context.getTestMethod()
				.map(Method::getParameterTypes)
				.map(Arrays::stream)
				.map(parameters -> parameters.anyMatch(Predicate.isEqual(String.class)))
				.orElse(false);
			// @formatter:on
		}
