		private static boolean initCacheSectionFlag(String line, @Nullable LineInfo previousLine) {
			if (MANIFEST_SECTION_HEADERS.contains(line.trim())) {
				return line.trim().equals(CACHE_HEADER);
			}
			else if (previousLine != null) {
				return previousLine.isCacheSection();
			}
			throw new IllegalStateException(
					"Manifest does not start with " + MANIFEST_HEADER + ": " + line);
		}
