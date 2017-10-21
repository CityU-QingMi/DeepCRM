	private void checkMatch(Class<?>[] inputTypes, Class<?>[] expectedTypes, StandardTypeConverter typeConverter, ArgumentsMatchKind expectedMatchKind) {
		ReflectionHelper.ArgumentsMatchInfo matchInfo = ReflectionHelper.compareArguments(getTypeDescriptors(expectedTypes), getTypeDescriptors(inputTypes), typeConverter);
		if (expectedMatchKind == null) {
			assertNull("Did not expect them to match in any way", matchInfo);
		}
		else {
			assertNotNull("Should not be a null match", matchInfo);
		}

		if (expectedMatchKind == ArgumentsMatchKind.EXACT) {
			assertTrue(matchInfo.isExactMatch());
		}
		else if (expectedMatchKind == ArgumentsMatchKind.CLOSE) {
			assertTrue(matchInfo.isCloseMatch());
		}
		else if (expectedMatchKind == ArgumentsMatchKind.REQUIRES_CONVERSION) {
			assertTrue("expected to be a match requiring conversion, but was " + matchInfo, matchInfo.isMatchRequiringConversion());
		}
	}
