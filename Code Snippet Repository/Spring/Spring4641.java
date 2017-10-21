	private String style(Collection<?> value) {
		StringBuilder result = new StringBuilder(value.size() * 8 + 16);
		result.append(getCollectionTypeString(value)).append('[');
		for (Iterator<?> i = value.iterator(); i.hasNext();) {
			result.append(style(i.next()));
			if (i.hasNext()) {
				result.append(',').append(' ');
			}
		}
		if (value.isEmpty()) {
			result.append(EMPTY);
		}
		result.append("]");
		return result.toString();
	}
