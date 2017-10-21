	private MultiValueMap<String, String> encodeQueryParams(Charset charset) {
		int size = this.queryParams.size();
		MultiValueMap<String, String> result = new LinkedMultiValueMap<>(size);
		for (Map.Entry<String, List<String>> entry : this.queryParams.entrySet()) {
			String name = encodeUriComponent(entry.getKey(), charset, Type.QUERY_PARAM);
			List<String> values = new ArrayList<>(entry.getValue().size());
			for (String value : entry.getValue()) {
				values.add(encodeUriComponent(value, charset, Type.QUERY_PARAM));
			}
			result.put(name, values);
		}
		return result;
	}
