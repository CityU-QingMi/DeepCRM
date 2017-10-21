	@Test
	public void defaultStyleMap() {
		final Map<String, String> map = getMap();
		Object stringy = new Object() {
			@Override
			public String toString() {
				return new ToStringCreator(this).append("familyFavoriteSport", map).toString();
			}
		};
		assertEquals("[ToStringCreatorTests.4@" + ObjectUtils.getIdentityHexString(stringy) +
				" familyFavoriteSport = map['Keri' -> 'Softball', 'Scot' -> 'Fishing', 'Keith' -> 'Flag Football']]",
				stringy.toString());
	}
