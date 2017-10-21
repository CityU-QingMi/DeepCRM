	@Test
	public void shouldCompareInterfaces() throws Exception {
		Comparator<Object> comparator = new InstanceComparator<>(I1.class, I2.class);
		assertThat(comparator.compare(c1, c1), is(0));
		assertThat(comparator.compare(c1, c2), is(0));
		assertThat(comparator.compare(c2, c1), is(0));
		assertThat(comparator.compare(c1, c3), is(-1));
		assertThat(comparator.compare(c3, c1), is(1));
		assertThat(comparator.compare(c3, c4), is(0));
	}
