	private void addUrlPattern(String urlPattern) {
		Assert.notNull(urlPattern, "Found null URL Pattern");
		if (urlPattern.startsWith(EXTENSION_MAPPING_PATTERN)) {
			this.endsWithMatches.add(urlPattern.substring(1, urlPattern.length()));
		}
		else if (urlPattern.equals(PATH_MAPPING_PATTERN)) {
			this.startsWithMatches.add("");
		}
		else if (urlPattern.endsWith(PATH_MAPPING_PATTERN)) {
			this.startsWithMatches.add(urlPattern.substring(0, urlPattern.length() - 1));
			this.exactMatches.add(urlPattern.substring(0, urlPattern.length() - 2));
		}
		else {
			if ("".equals(urlPattern)) {
				urlPattern = "/";
			}
			this.exactMatches.add(urlPattern);
		}
	}
