	public ResultMatcher attributeHasNoErrors(final String... names) {
		return mvcResult -> {
			ModelAndView mav = getModelAndView(mvcResult);
			for (String name : names) {
				BindingResult result = getBindingResult(mav, name);
				assertTrue("Unexpected errors for attribute '" + name + "': " + result.getAllErrors(),
						!result.hasErrors());
			}
		};
	}
