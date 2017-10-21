	private int getQueryIndex(String path) {
		int suffixIndex = path.length();
		int queryIndex = path.indexOf("?");
		if (queryIndex > 0) {
			suffixIndex = queryIndex;
		}
		int hashIndex = path.indexOf("#");
		if (hashIndex > 0) {
			suffixIndex = Math.min(suffixIndex, hashIndex);
		}
		return suffixIndex;
	}
