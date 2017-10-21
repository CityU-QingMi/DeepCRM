		@Override
		protected Object getTestInstance() throws Exception {
			if (testInstance == null) {
				if (fieldsAreAnnotated()) {
					testInstance = createTestUsingFieldInjection();
				}
				else {
					testInstance = createTestUsingConstructorInjection();
				}
			}
			return testInstance;
		}
