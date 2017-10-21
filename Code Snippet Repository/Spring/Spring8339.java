	public <T> ResultMatcher hasNoErrors() {
		return result -> {
			ModelAndView mav = getModelAndView(result);
			for (Object value : mav.getModel().values()) {
				if (value instanceof Errors) {
					assertTrue("Unexpected binding/validation errors: " + value, !((Errors) value).hasErrors());
				}
			}
		};
	}
