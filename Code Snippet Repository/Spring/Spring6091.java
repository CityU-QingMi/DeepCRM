	private static void populateFunctions(StandardEvaluationContext testContext) {
		try {
			testContext.registerFunction("isEven",
					TestScenarioCreator.class.getDeclaredMethod("isEven", Integer.TYPE));
			testContext.registerFunction("reverseInt",
					TestScenarioCreator.class.getDeclaredMethod("reverseInt", Integer.TYPE, Integer.TYPE, Integer.TYPE));
			testContext.registerFunction("reverseString",
					TestScenarioCreator.class.getDeclaredMethod("reverseString", String.class));
			testContext.registerFunction("varargsFunctionReverseStringsAndMerge",
					TestScenarioCreator.class.getDeclaredMethod("varargsFunctionReverseStringsAndMerge", String[].class));
			testContext.registerFunction("varargsFunctionReverseStringsAndMerge2",
					TestScenarioCreator.class.getDeclaredMethod("varargsFunctionReverseStringsAndMerge2", Integer.TYPE, String[].class));
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}
