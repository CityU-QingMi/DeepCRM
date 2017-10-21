	public static String toString(Collection<HttpRange> ranges) {
		Assert.notEmpty(ranges, "Ranges Collection must not be empty");
		StringBuilder builder = new StringBuilder(BYTE_RANGE_PREFIX);
		for (Iterator<HttpRange> iterator = ranges.iterator(); iterator.hasNext(); ) {
			HttpRange range = iterator.next();
			builder.append(range);
			if (iterator.hasNext()) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}
