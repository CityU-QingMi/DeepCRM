	public String getParameter(String name) {
		// Check if the parameter is overriden in the extra params
		if (extraParams.containsKey(name)) {
			String[] values = extraParams.get(name);
			if (values != null && values.length > 0) {
				return values[0];
			}
		}
		return portletRequest.getParameter(name);
	}
