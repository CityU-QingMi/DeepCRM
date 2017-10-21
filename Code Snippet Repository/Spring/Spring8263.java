	@SuppressWarnings("")
	public static <T> T assertAndReturnModelAttributeOfType(ModelAndView mav, String modelName, Class<T> expectedType) {
		if (mav == null) {
			fail("ModelAndView is null");
		}
		Map<String, Object> model = mav.getModel();
		Object obj = model.get(modelName);
		if (obj == null) {
			fail("Model attribute with name '" + modelName + "' is null");
		}
		assertTrue("Model attribute is not of expected type '" + expectedType.getName() + "' but rather of type '" +
				obj.getClass().getName() + "'", expectedType.isAssignableFrom(obj.getClass()));
		return (T) obj;
	}
