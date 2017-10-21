	@Test
	@SuppressWarnings("")
	public void withSecurityConstraints() throws Exception {
		envMap = new HashMap<String, Object>() {
			@Override
			public boolean containsKey(Object key) {
				throw new UnsupportedOperationException();
			}
			@Override
			public Set<String> keySet() {
				return new HashSet<>(super.keySet());
			}
		};
		envMap.put("A_KEY", "a_value");

		ps = new SystemEnvironmentPropertySource("sysEnv", envMap) {
			@Override
			protected boolean isSecurityManagerPresent() {
				return true;
			}
		};

		assertThat(ps.containsProperty("A_KEY"), equalTo(true));
		assertThat(ps.getProperty("A_KEY"), equalTo((Object)"a_value"));
	}
