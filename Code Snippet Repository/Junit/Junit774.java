	@Override
	public String apply(Serializable uniqueId) {
		if (uniqueId instanceof CharSequence) {
			return uniqueId.toString();
		}
		if (uniqueId instanceof Number) {
			return NumberFormat.getInstance(Locale.US).format(uniqueId);
		}
		return encodeBase64(serialize(uniqueId));
	}
