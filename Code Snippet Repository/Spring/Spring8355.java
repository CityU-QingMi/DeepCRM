	private boolean matches(String requestPath) {
		for (String pattern : this.exactMatches) {
			if (pattern.equals(requestPath)) {
				return true;
			}
		}
		if (!requestPath.startsWith("/")) {
			return false;
		}
		for (String pattern : this.endsWithMatches) {
			if (requestPath.endsWith(pattern)) {
				return true;
			}
		}
		for (String pattern : this.startsWithMatches) {
			if (requestPath.startsWith(pattern)) {
				return true;
			}
		}
		return false;
	}
