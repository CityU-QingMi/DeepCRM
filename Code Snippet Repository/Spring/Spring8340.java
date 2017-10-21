	public <T> ResultMatcher size(final int size) {
		return result -> {
			ModelAndView mav = getModelAndView(result);
			int actual = 0;
			for (String key : mav.getModel().keySet()) {
				if (!key.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
					actual++;
				}
			}
			assertEquals("Model size", size, actual);
		};
	}
