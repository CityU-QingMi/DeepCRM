	private String prepareMessageFormatPattern(int invocationIndex, Object[] arguments) {
		String result = namePattern.replace("{index}", String.valueOf(invocationIndex));
		if (result.contains("{arguments}")) {
			// @formatter:off
			String replacement = IntStream.range(0, arguments.length)
					.mapToObj(index -> "{" + index + "}")
					.collect(joining(", "));
			// @formatter:on
			result = result.replace("{arguments}", replacement);
		}
		return result;
	}
