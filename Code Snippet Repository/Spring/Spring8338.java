	public <T> ResultMatcher attributeHasFieldErrorCode(final String name, final String fieldName,
			final Matcher<? super String> matcher) {

		return mvcResult -> {
			ModelAndView mav = getModelAndView(mvcResult);
			BindingResult result = getBindingResult(mav, name);
			assertTrue("No errors for attribute: [" + name + "]", result.hasErrors());
			FieldError fieldError = result.getFieldError(fieldName);
			if (fieldError == null) {
				fail("No errors for field '" + fieldName + "' of attribute '" + name + "'");
			}
			String code = fieldError.getCode();
			assertThat("Field name '" + fieldName + "' of attribute '" + name + "'", code, matcher);
		};
	}
