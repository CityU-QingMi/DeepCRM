	public static void assertModelAttributeValues(ModelAndView mav, Map<String, Object> expectedModel) {
		if (mav == null) {
			fail("ModelAndView is null");
		}
		Map<String, Object> model = mav.getModel();

		if (!model.keySet().equals(expectedModel.keySet())) {
			StringBuilder sb = new StringBuilder("Keyset of expected model does not match.\n");
			appendNonMatchingSetsErrorMessage(expectedModel.keySet(), model.keySet(), sb);
			fail(sb.toString());
		}

		StringBuilder sb = new StringBuilder();
		for (String modelName : model.keySet()) {
			Object assertionValue = expectedModel.get(modelName);
			Object mavValue = model.get(modelName);
			if (!assertionValue.equals(mavValue)) {
				sb.append("Value under name '").append(modelName).append("' differs, should have been '").append(
					assertionValue).append("' but was '").append(mavValue).append("'\n");
			}
		}

		if (sb.length() != 0) {
			sb.insert(0, "Values of expected model do not match.\n");
			fail(sb.toString());
		}
	}
