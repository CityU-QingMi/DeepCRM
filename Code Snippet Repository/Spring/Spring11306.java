	public Map<String, Object> retrieveAttributes(WebSession session) {
		Map<String, Object> attributes = new HashMap<>();
		this.knownAttributeNames.forEach(name -> {
			Object value = session.getAttribute(name);
			if (value != null) {
				attributes.put(name, value);
			}
		});
		return attributes;
	}
