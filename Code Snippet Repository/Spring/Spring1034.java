	@Test
	public void getPropertyValueAutoGrowArrayBySeveralElements() {
		assertNotNull(wrapper.getPropertyValue("array[4]"));
		assertEquals(5, bean.getArray().length);
		assertThat(bean.getArray()[0], instanceOf(Bean.class));
		assertThat(bean.getArray()[1], instanceOf(Bean.class));
		assertThat(bean.getArray()[2], instanceOf(Bean.class));
		assertThat(bean.getArray()[3], instanceOf(Bean.class));
		assertThat(bean.getArray()[4], instanceOf(Bean.class));
		assertNotNull(wrapper.getPropertyValue("array[0]"));
		assertNotNull(wrapper.getPropertyValue("array[1]"));
		assertNotNull(wrapper.getPropertyValue("array[2]"));
		assertNotNull(wrapper.getPropertyValue("array[3]"));
	}
