	@Nullable
	static ArgumentsMatchInfo compareArguments(
			List<TypeDescriptor> expectedArgTypes, List<TypeDescriptor> suppliedArgTypes, TypeConverter typeConverter) {

		Assert.isTrue(expectedArgTypes.size() == suppliedArgTypes.size(),
				"Expected argument types and supplied argument types should be arrays of same length");

		ArgumentsMatchKind match = ArgumentsMatchKind.EXACT;
		for (int i = 0; i < expectedArgTypes.size() && match != null; i++) {
			TypeDescriptor suppliedArg = suppliedArgTypes.get(i);
			TypeDescriptor expectedArg = expectedArgTypes.get(i);
			// The user may supply null - and that will be ok unless a primitive is expected
			if (suppliedArg == null) {
				if (expectedArg.isPrimitive()) {
					match = null;
				}
			}
			else if (!expectedArg.equals(suppliedArg))  {
				if (suppliedArg.isAssignableTo(expectedArg)) {
					if (match != ArgumentsMatchKind.REQUIRES_CONVERSION) {
						match = ArgumentsMatchKind.CLOSE;
					}
				}
				else if (typeConverter.canConvert(suppliedArg, expectedArg)) {
					match = ArgumentsMatchKind.REQUIRES_CONVERSION;
				}
				else {
					match = null;
				}
			}
		}
		return (match != null ? new ArgumentsMatchInfo(match) : null);
	}
