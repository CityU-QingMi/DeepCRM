	public void addNativeHeader(String name, @Nullable String value) {
		Assert.state(isMutable(), "Already immutable");
		if (value == null) {
			return;
		}
		Map<String, List<String>> nativeHeaders = getNativeHeaders();
		if (nativeHeaders == null) {
			nativeHeaders = new LinkedMultiValueMap<>(4);
			setHeader(NATIVE_HEADERS, nativeHeaders);
		}
		List<String> values = nativeHeaders.get(name);
		if (values == null) {
			values = new LinkedList<>();
			nativeHeaders.put(name, values);
		}
		values.add(value);
		setModified(true);
	}
