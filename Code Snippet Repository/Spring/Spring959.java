	@Override
	protected Method findMatchingMethod() {
		Method matchingMethod = super.findMatchingMethod();
		// Second pass: look for method where arguments can be converted to parameter types.
		if (matchingMethod == null) {
			// Interpret argument array as individual method arguments.
			matchingMethod = doFindMatchingMethod(getArguments());
		}
		if (matchingMethod == null) {
			// Interpret argument array as single method argument of array type.
			matchingMethod = doFindMatchingMethod(new Object[] {getArguments()});
		}
		return matchingMethod;
	}
