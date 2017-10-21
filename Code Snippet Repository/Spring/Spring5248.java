	@Test
	public void withSingleCustomReservedDefaultProfile() {
		class CustomEnvironment extends AbstractEnvironment {
			@Override
			protected Set<String> getReservedDefaultProfiles() {
				return Collections.singleton("rd1");
			}
		}

		Environment env = new CustomEnvironment();
		assertThat(env.acceptsProfiles(AbstractEnvironment.RESERVED_DEFAULT_PROFILE_NAME), is(false));
		assertThat(env.acceptsProfiles("rd1"), is(true));
	}
