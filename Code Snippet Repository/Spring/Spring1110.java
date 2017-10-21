	@Test
	public void propertyDescriptorComparator() throws IntrospectionException {
		ExtendedBeanInfo.PropertyDescriptorComparator c = new ExtendedBeanInfo.PropertyDescriptorComparator();

		assertThat(c.compare(new PropertyDescriptor("a", null, null), new PropertyDescriptor("a", null, null)), equalTo(0));
		assertThat(c.compare(new PropertyDescriptor("abc", null, null), new PropertyDescriptor("abc", null, null)), equalTo(0));
		assertThat(c.compare(new PropertyDescriptor("a", null, null), new PropertyDescriptor("b", null, null)), lessThan(0));
		assertThat(c.compare(new PropertyDescriptor("b", null, null), new PropertyDescriptor("a", null, null)), greaterThan(0));
		assertThat(c.compare(new PropertyDescriptor("abc", null, null), new PropertyDescriptor("abd", null, null)), lessThan(0));
		assertThat(c.compare(new PropertyDescriptor("xyz", null, null), new PropertyDescriptor("123", null, null)), greaterThan(0));
		assertThat(c.compare(new PropertyDescriptor("a", null, null), new PropertyDescriptor("abc", null, null)), lessThan(0));
		assertThat(c.compare(new PropertyDescriptor("abc", null, null), new PropertyDescriptor("a", null, null)), greaterThan(0));
		assertThat(c.compare(new PropertyDescriptor("abc", null, null), new PropertyDescriptor("b", null, null)), lessThan(0));

		assertThat(c.compare(new PropertyDescriptor(" ", null, null), new PropertyDescriptor("a", null, null)), lessThan(0));
		assertThat(c.compare(new PropertyDescriptor("1", null, null), new PropertyDescriptor("a", null, null)), lessThan(0));
		assertThat(c.compare(new PropertyDescriptor("a", null, null), new PropertyDescriptor("A", null, null)), greaterThan(0));
	}
