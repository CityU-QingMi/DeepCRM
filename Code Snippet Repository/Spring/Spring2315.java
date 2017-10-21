	protected List<String> calculateAllFilenames(String basename, Locale locale) {
		Map<Locale, List<String>> localeMap = this.cachedFilenames.get(basename);
		if (localeMap != null) {
			List<String> filenames = localeMap.get(locale);
			if (filenames != null) {
				return filenames;
			}
		}
		List<String> filenames = new ArrayList<>(7);
		filenames.addAll(calculateFilenamesForLocale(basename, locale));
		if (isFallbackToSystemLocale() && !locale.equals(Locale.getDefault())) {
			List<String> fallbackFilenames = calculateFilenamesForLocale(basename, Locale.getDefault());
			for (String fallbackFilename : fallbackFilenames) {
				if (!filenames.contains(fallbackFilename)) {
					// Entry for fallback locale that isn't already in filenames list.
					filenames.add(fallbackFilename);
				}
			}
		}
		filenames.add(basename);
		if (localeMap == null) {
			localeMap = new ConcurrentHashMap<>();
			Map<Locale, List<String>> existing = this.cachedFilenames.putIfAbsent(basename, localeMap);
			if (existing != null) {
				localeMap = existing;
			}
		}
		localeMap.put(locale, filenames);
		return filenames;
	}
