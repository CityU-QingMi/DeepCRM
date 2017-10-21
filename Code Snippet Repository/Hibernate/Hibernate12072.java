		@Override
		protected void validateFields(List<Throwable> errors) {
			super.validateFields(errors);
			if (fieldsAreAnnotated()) {
				List<FrameworkField> annotatedFieldsByParameter = getAnnotatedFieldsByParameter();
				int[] usedIndices = new int[annotatedFieldsByParameter.size()];
				for (FrameworkField each : annotatedFieldsByParameter) {
					int index = each.getField().getAnnotation(Parameterized.Parameter.class).value();
					if (index < 0 || index > annotatedFieldsByParameter.size() - 1) {
						errors.add(
								new Exception("Invalid @Parameter value: " + index + ". @Parameter fields counted: " +
										annotatedFieldsByParameter.size() + ". Please use an index between 0 and " +
										(annotatedFieldsByParameter.size() - 1) + ".")
						);
					}
					else {
						usedIndices[index]++;
					}
				}
				for (int index = 0; index < usedIndices.length; index++) {
					int numberOfUse = usedIndices[index];
					if (numberOfUse == 0) {
						errors.add(new Exception("@Parameter(" + index + ") is never used."));
					}
					else if (numberOfUse > 1) {
						errors.add(new Exception("@Parameter(" + index + ") is used more than once (" + numberOfUse + ")."));
					}
				}
			}
		}
