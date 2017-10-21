	private static Filter matchMethodDescription(final Description desiredDescription) {
		return new Filter() {

			@Override
			public boolean shouldRun(Description description) {
				if (description.isTest()) {
					return desiredDescription.equals(description) || isParameterizedMethod(description);
				}

				// explicitly check if any children want to run
				for (Description each : description.getChildren()) {
					if (shouldRun(each)) {
						return true;
					}
				}
				return false;
			}

			private boolean isParameterizedMethod(Description description) {
				return description.getMethodName().startsWith(desiredDescription.getMethodName() + "[");
			}

			@Override
			public String describe() {
				return String.format("Method %s", desiredDescription.getDisplayName());
			}
		};
	}
