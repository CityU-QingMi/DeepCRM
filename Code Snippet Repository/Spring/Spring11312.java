	private boolean isMatch(Map.Entry<String, ?> entry) {
		if (entry.getValue() == null) {
			return false;
		}
		if (!getModelKeys().isEmpty() && !getModelKeys().contains(entry.getKey())) {
			return false;
		}
		ResolvableType type = ResolvableType.forInstance(entry.getValue());
		return getMessageWriter().canWrite(type, null);
	}
