	public static Country getCountryWithIsoCode(final String isoCode) {
		if (COUNTRY_AT.isoCode.equals(isoCode)) {
			return COUNTRY_AT;
		}
		if (COUNTRY_NL.isoCode.equals(isoCode)) {
			return COUNTRY_NL;
		}
		if (COUNTRY_UK.isoCode.equals(isoCode)) {
			return COUNTRY_UK;
		}
		if (COUNTRY_US.isoCode.equals(isoCode)) {
			return COUNTRY_US;
		}
		return null;
	}
