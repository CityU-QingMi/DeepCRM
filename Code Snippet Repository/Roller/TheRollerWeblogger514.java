	private void addEntryToResults(WeblogEntryWrapper entry) {

		// convert entry's each date to midnight (00m 00h 00s)
		Date midnight = DateUtil.getStartOfDay(entry.getPubTime());

		// ensure we do not get duplicates from Lucene by
		// using a Set Collection. Entries sorted by pubTime.
		TreeSet<WeblogEntryWrapper> set = this.results.get(midnight);
		if (set == null) {
			// date is not mapped yet, so we need a new Set
			set = new TreeSet<WeblogEntryWrapper>(new WeblogEntryWrapperComparator());
			this.results.put(midnight, set);
		}
		set.add(entry);
	}
