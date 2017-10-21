	@Test
	@SuppressWarnings("")
	public void equals() {
		Map<String, Object> map1 = new HashMap<String, Object>() {{ put("a", "b"); }};
		Map<String, Object> map2 = new HashMap<String, Object>() {{ put("c", "d"); }};
		Properties props1 = new Properties() {{ setProperty("a", "b"); }};
		Properties props2 = new Properties() {{ setProperty("c", "d"); }};

		MapPropertySource mps = new MapPropertySource("mps", map1);
		assertThat(mps, equalTo(mps));

		assertThat(new MapPropertySource("x", map1).equals(new MapPropertySource("x", map1)), is(true));
		assertThat(new MapPropertySource("x", map1).equals(new MapPropertySource("x", map2)), is(true));
		assertThat(new MapPropertySource("x", map1).equals(new PropertiesPropertySource("x", props1)), is(true));
		assertThat(new MapPropertySource("x", map1).equals(new PropertiesPropertySource("x", props2)), is(true));

		assertThat(new MapPropertySource("x", map1).equals(new Object()), is(false));
		assertThat(new MapPropertySource("x", map1).equals("x"), is(false));
		assertThat(new MapPropertySource("x", map1).equals(new MapPropertySource("y", map1)), is(false));
		assertThat(new MapPropertySource("x", map1).equals(new MapPropertySource("y", map2)), is(false));
		assertThat(new MapPropertySource("x", map1).equals(new PropertiesPropertySource("y", props1)), is(false));
		assertThat(new MapPropertySource("x", map1).equals(new PropertiesPropertySource("y", props2)), is(false));
	}
