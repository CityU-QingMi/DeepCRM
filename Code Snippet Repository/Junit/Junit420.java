		private TestTemplateInvocationContext createContext(String argument) {
			return new TestTemplateInvocationContext() {

				@Override
				public String getDisplayName(int invocationIndex) {
					return argument;
				}

				@Override
				public List<Extension> getAdditionalExtensions() {
					return singletonList(new LifecycleCallbackExtension());
				}
			};
		}
