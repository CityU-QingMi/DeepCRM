		@Override
		public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
			return Stream.<TestTemplateInvocationContext> generate(() -> new TestTemplateInvocationContext() {

				@Override
				public String getDisplayName(int invocationIndex) {
					return invocationIndex + " --> " + context.getDisplayName();
				}
			}).limit(1);
		}
