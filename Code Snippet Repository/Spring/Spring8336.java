	public ResultMatcher attributeHasFieldErrors(final String name, final String... fieldNames) {
		return mvcResult -> {
			ModelAndView mav = getModelAndView(mvcResult);
			BindingResult result = getBindingResult(mav, name);
			assertTrue("No errors for attribute '" + name + "'", result.hasErrors());
			for (final String fieldName : fieldNames) {
				boolean hasFieldErrors = result.hasFieldErrors(fieldName);
				assertTrue("No errors for field '" + fieldName + "' of attribute '" + name + "'", hasFieldErrors);
			}
		};
	}
