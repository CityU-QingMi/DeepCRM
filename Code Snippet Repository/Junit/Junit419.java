		private TestTemplateInvocationContext createContext(String argument) {
			return new TestTemplateInvocationContext() {

				@Override
				public String getDisplayName(int invocationIndex) {
					return TestTemplateInvocationContext.super.getDisplayName(invocationIndex) + " " + argument;
				}

				@Override
				public List<Extension> getAdditionalExtensions() {
					return singletonList((TestInstancePostProcessor) (testInstance, context) -> {
						Field field = testInstance.getClass().getDeclaredField("parameterInstanceVariable");
						field.setAccessible(true);
						field.set(testInstance, argument);
					});
				}
			};
		}
