	@Test
	public void withMultiCustomReservedDefaultProfile() {
		class CustomEnvironment extends AbstractEnvironment {
			@Override
			@SuppressWarnings("serial")
			protected Set<String> getReservedDefaultProfiles() {
				return new HashSet<String>() {{ add("rd1"); add("rd2");  }};
			}
		}

		ConfigurableEnvironment env = new CustomEnvironment();
		assertThat(env.acceptsProfiles(AbstractEnvironment.RESERVED_DEFAULT_PROFILE_NAME), is(false));
		assertThat(env.acceptsProfiles("rd1", "rd2"), is(true));

		// finally, issue additional assertions to cover all combinations of calling these
		// methods, however unlikely.
		env.setDefaultProfiles("d1");
		assertThat(env.acceptsProfiles("rd1", "rd2"), is(false));
		assertThat(env.acceptsProfiles("d1"), is(true));

		env.setActiveProfiles("a1", "a2");
		assertThat(env.acceptsProfiles("d1"), is(false));
		assertThat(env.acceptsProfiles("a1", "a2"), is(true));

		env.setActiveProfiles();
		assertThat(env.acceptsProfiles("d1"), is(true));
		assertThat(env.acceptsProfiles("a1", "a2"), is(false));

		env.setDefaultProfiles();
		assertThat(env.acceptsProfiles(AbstractEnvironment.RESERVED_DEFAULT_PROFILE_NAME), is(false));
		assertThat(env.acceptsProfiles("rd1", "rd2"), is(false));
		assertThat(env.acceptsProfiles("d1"), is(false));
		assertThat(env.acceptsProfiles("a1", "a2"), is(false));
	}
