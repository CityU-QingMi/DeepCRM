	@Test
	public void getPropertyValueAutoGrowListBySeveralElements() {
		assertNotNull(wrapper.getPropertyValue("list[4]"));
		assertEquals(5, bean.getList().size());
		assertThat(bean.getList().get(0), instanceOf(Bean.class));
		assertThat(bean.getList().get(1), instanceOf(Bean.class));
		assertThat(bean.getList().get(2), instanceOf(Bean.class));
		assertThat(bean.getList().get(3), instanceOf(Bean.class));
		assertThat(bean.getList().get(4), instanceOf(Bean.class));
		assertNotNull(wrapper.getPropertyValue("list[0]"));
		assertNotNull(wrapper.getPropertyValue("list[1]"));
		assertNotNull(wrapper.getPropertyValue("list[2]"));
		assertNotNull(wrapper.getPropertyValue("list[3]"));
	}
