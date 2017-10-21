		private Object createTestUsingFieldInjection() throws Exception {
			List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
			if (annotatedFieldsByParameter.size() != parameters.length) {
				throw new Exception("Wrong number of parameters and @Parameter fields." +
						" @Parameter fields counted: " + annotatedFieldsByParameter.size() + ", available parameters: " + parameters.length + ".");
			}
			Object testClassInstance = getTestClass().getJavaClass().newInstance();
			for (FrameworkField each : annotatedFieldsByParameter) {
				Field field = each.getField();
				Parameterized.Parameter annotation = field.getAnnotation(Parameterized.Parameter.class);
				int index = annotation.value();
				try {
					field.set(testClassInstance, parameters[index]);
				}
				catch (IllegalArgumentException iare) {
					throw new Exception(getTestClass().getName() + ": Trying to set " + field.getName() +
							" with the value " + parameters[index] +
							" that is not the right type (" + parameters[index].getClass().getSimpleName() + " instead of " +
							field.getType().getSimpleName() + ").", iare);
				}
			}
			return testClassInstance;
		}
