	private static boolean isTestEnabledInThisEnvironment(ProfileValueSource profileValueSource,
			@Nullable IfProfileValue ifProfileValue) {

		if (ifProfileValue == null) {
			return true;
		}

		String environmentValue = profileValueSource.get(ifProfileValue.name());
		String[] annotatedValues = ifProfileValue.values();
		if (StringUtils.hasLength(ifProfileValue.value())) {
			Assert.isTrue(annotatedValues.length == 0, () -> "Setting both the 'value' and 'values' attributes " +
						"of @IfProfileValue is not allowed: choose one or the other.");
			annotatedValues = new String[] { ifProfileValue.value() };
		}

		for (String value : annotatedValues) {
			if (ObjectUtils.nullSafeEquals(value, environmentValue)) {
				return true;
			}
		}
		return false;
	}
