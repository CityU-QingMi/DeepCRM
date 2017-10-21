	@Test(expected = IllegalArgumentException.class)
	public void defaultProfileWithCircularPlaceholder() {
		System.setProperty(DEFAULT_PROFILES_PROPERTY_NAME, "${spring.profiles.default}");
		try {
			environment.getDefaultProfiles();
		}
		finally {
			System.getProperties().remove(DEFAULT_PROFILES_PROPERTY_NAME);
		}
	}
