	private static MultiValueMap<String, String> parseIndex(List<Properties> content) {
		MultiValueMap<String, String> index = new LinkedMultiValueMap<>();
		for (Properties entry : content) {
			entry.forEach((type, values) -> {
				String[] stereotypes = ((String) values).split(",");
				for (String stereotype : stereotypes) {
					index.add(stereotype, (String) type);
				}
			});
		}
		return index;
	}
