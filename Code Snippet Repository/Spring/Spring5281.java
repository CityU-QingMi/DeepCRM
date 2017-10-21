	@Test
	public void environmentSubclass_withCustomProfileValidation() {
		ConfigurableEnvironment env = new AbstractEnvironment() {
			@Override
			protected void validateProfile(String profile) {
				super.validateProfile(profile);
				if (profile.contains("-")) {
					throw new IllegalArgumentException(
							"Invalid profile [" + profile + "]: must not contain dash character");
				}
			}
		};

		env.addActiveProfile("validProfile"); // succeeds

		try {
			env.addActiveProfile("invalid-profile");
			fail("expected validation exception");
		}
		catch (IllegalArgumentException ex) {
			assertThat(ex.getMessage(),
					equalTo("Invalid profile [invalid-profile]: must not contain dash character"));
		}
	}
