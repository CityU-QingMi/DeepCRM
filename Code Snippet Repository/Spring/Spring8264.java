	@SuppressWarnings("")
	public static void assertCompareListModelAttribute(ModelAndView mav, String modelName, List expectedList) {
		assertTrue("ModelAndView is null", mav != null);
		List modelList = assertAndReturnModelAttributeOfType(mav, modelName, List.class);
		assertTrue(
			"Size of model list is '" + modelList.size() + "' while size of expected list is '" + expectedList.size()
					+ "'", expectedList.size() == modelList.size());
		assertTrue("List in model under name '" + modelName + "' is not equal to the expected list.",
			expectedList.equals(modelList));
	}
