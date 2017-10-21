	@Test
	public void shouldConfigureFixedPrefixAutomatically() throws Exception {

		this.resolver.addFixedVersionStrategy("fixedversion", "/js/**", "/css/**", "/fixedversion/css/**");

		assertThat(this.resolver.getStrategyMap().size(), is(4));
		assertThat(this.resolver.getStrategyForPath("js/something.js"), Matchers.instanceOf(FixedVersionStrategy.class));
		assertThat(this.resolver.getStrategyForPath("fixedversion/js/something.js"), Matchers.instanceOf(FixedVersionStrategy.class));
		assertThat(this.resolver.getStrategyForPath("css/something.css"), Matchers.instanceOf(FixedVersionStrategy.class));
		assertThat(this.resolver.getStrategyForPath("fixedversion/css/something.css"), Matchers.instanceOf(FixedVersionStrategy.class));
	}
