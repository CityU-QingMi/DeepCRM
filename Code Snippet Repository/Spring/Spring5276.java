	@Test
	public void merge() {
		ConfigurableEnvironment child = new StandardEnvironment();
		child.setActiveProfiles("c1", "c2");
		child.getPropertySources().addLast(
				new MockPropertySource("childMock")
						.withProperty("childKey", "childVal")
						.withProperty("bothKey", "childBothVal"));

		ConfigurableEnvironment parent = new StandardEnvironment();
		parent.setActiveProfiles("p1", "p2");
		parent.getPropertySources().addLast(
				new MockPropertySource("parentMock")
						.withProperty("parentKey", "parentVal")
						.withProperty("bothKey", "parentBothVal"));

		assertThat(child.getProperty("childKey"), is("childVal"));
		assertThat(child.getProperty("parentKey"), nullValue());
		assertThat(child.getProperty("bothKey"), is("childBothVal"));

		assertThat(parent.getProperty("childKey"), nullValue());
		assertThat(parent.getProperty("parentKey"), is("parentVal"));
		assertThat(parent.getProperty("bothKey"), is("parentBothVal"));

		assertThat(child.getActiveProfiles(), equalTo(new String[]{"c1","c2"}));
		assertThat(parent.getActiveProfiles(), equalTo(new String[]{"p1","p2"}));

		child.merge(parent);

		assertThat(child.getProperty("childKey"), is("childVal"));
		assertThat(child.getProperty("parentKey"), is("parentVal"));
		assertThat(child.getProperty("bothKey"), is("childBothVal"));

		assertThat(parent.getProperty("childKey"), nullValue());
		assertThat(parent.getProperty("parentKey"), is("parentVal"));
		assertThat(parent.getProperty("bothKey"), is("parentBothVal"));

		assertThat(child.getActiveProfiles(), equalTo(new String[]{"c1","c2","p1","p2"}));
		assertThat(parent.getActiveProfiles(), equalTo(new String[]{"p1","p2"}));
	}
