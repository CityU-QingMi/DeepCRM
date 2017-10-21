	@Override
	public String extractVersion(String requestPath) {
		Matcher matcher = pattern.matcher(requestPath);
		if (matcher.find()) {
			String match = matcher.group(1);
			return (match.contains("-") ? match.substring(match.lastIndexOf('-') + 1) : match);
		}
		else {
			return null;
		}
	}
