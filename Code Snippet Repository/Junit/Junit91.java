	private Predicate<Object> conditionIsActivated(ConfigurationParameters configurationParameters) {
		String patternString = getDeactivatePatternString(configurationParameters);
		if (patternString != null) {
			if (DEACTIVATE_ALL_CONDITIONS_PATTERN.equals(patternString)) {
				return alwaysDeactivated;
			}
			Pattern pattern = Pattern.compile(convertToRegEx(patternString));
			return condition -> !pattern.matcher(condition.getClass().getName()).matches();
		}
		return alwaysActivated;
	}
