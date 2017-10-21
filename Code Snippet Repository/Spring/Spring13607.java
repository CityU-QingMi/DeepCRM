	private Map getCountryToLocaleMap() {
		Map map = new TreeMap(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((Country)o1).getName().compareTo(((Country)o2).getName());
			}
		});
		map.put(Country.COUNTRY_AT, LOCALE_AT);
		map.put(Country.COUNTRY_NL, LOCALE_NL);
		map.put(Country.COUNTRY_US, Locale.US);
		return map;
	}
