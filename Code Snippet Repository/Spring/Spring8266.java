	@SuppressWarnings({ "", "" })
	public static void assertSortAndCompareListModelAttribute(
			ModelAndView mav, String modelName, List expectedList, Comparator comparator) {

		assertTrue("ModelAndView is null", mav != null);
		List modelList = assertAndReturnModelAttributeOfType(mav, modelName, List.class);

		assertTrue(
			"Size of model list is '" + modelList.size() + "' while size of expected list is '" + expectedList.size()
					+ "'", expectedList.size() == modelList.size());

		if (comparator != null) {
			Collections.sort(modelList, comparator);
			Collections.sort(expectedList, comparator);
		}
		else {
			Collections.sort(modelList);
			Collections.sort(expectedList);
		}

		assertTrue("List in model under name '" + modelName + "' is not equal to the expected list.",
			expectedList.equals(modelList));
	}
