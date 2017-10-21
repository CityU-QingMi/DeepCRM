	private String getTechnicalName(TestIdentifier testIdentifier) {
		Optional<TestSource> optionalSource = testIdentifier.getSource();
		if (optionalSource.isPresent()) {
			TestSource source = optionalSource.get();
			if (source instanceof ClassSource) {
				return ((ClassSource) source).getJavaClass().getName();
			}
			else if (source instanceof MethodSource) {
				MethodSource methodSource = (MethodSource) source;
				String methodParameterTypes = methodSource.getMethodParameterTypes();
				if (StringUtils.isBlank(methodParameterTypes)) {
					return methodSource.getMethodName();
				}
				return String.format("%s(%s)", methodSource.getMethodName(), methodParameterTypes);
			}
		}

		// Else fall back to display name
		return testIdentifier.getDisplayName();
	}
