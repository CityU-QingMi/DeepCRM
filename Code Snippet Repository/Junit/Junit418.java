		private TestTemplateInvocationContext createContext(String argument) {
			return new TestTemplateInvocationContext() {

				@Override
				public String getDisplayName(int invocationIndex) {
					return TestTemplateInvocationContext.super.getDisplayName(invocationIndex) + " " + argument;
				}

				@Override
				public List<Extension> getAdditionalExtensions() {
					return singletonList(new ParameterResolver() {

						@Override
						public boolean supportsParameter(ParameterContext parameterContext,
								ExtensionContext extensionContext) throws ParameterResolutionException {
							return true;
						}

						@Override
						public Object resolveParameter(ParameterContext parameterContext,
								ExtensionContext extensionContext) throws ParameterResolutionException {
							return argument;
						}
					});
				}
			};
		}
