	@Test
	public void repro() {
		assertThat(bf.getBean("foo"), instanceOf(Foo.class));
		assertThat(bf.getBean("&foo"), instanceOf(FooFactoryBean.class));
		assertThat(bf.isTypeMatch("&foo", FactoryBean.class), is(true));

		@SuppressWarnings("rawtypes")
		Map<String, FactoryBean> fbBeans = bf.getBeansOfType(FactoryBean.class);
		assertThat(fbBeans.size(), is(1));
		assertThat(fbBeans.keySet(), hasItem("&foo"));

		Map<String, AnInterface> aiBeans = bf.getBeansOfType(AnInterface.class);
		assertThat(aiBeans.size(), is(1));
		assertThat(aiBeans.keySet(), hasItem("&foo"));
	}
