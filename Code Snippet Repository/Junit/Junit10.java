		private TestTemplateInvocationContext invocationContext(String parameter) {
			return new TestTemplateInvocationContext() {
				@Override
				public String getDisplayName(int invocationIndex) {
					return parameter;
				}

				@Override
				public List<Extension> getAdditionalExtensions() {
					return Collections.singletonList(new ParameterResolver() {
						@Override
						public boolean supportsParameter(ParameterContext parameterContext,
								ExtensionContext extensionContext) {
							return parameterContext.getParameter().getType().equals(String.class);
						}

						@Override
						public Object resolveParameter(ParameterContext parameterContext,
								ExtensionContext extensionContext) {
							return parameter;
						}
					});
				}
			};
		}
