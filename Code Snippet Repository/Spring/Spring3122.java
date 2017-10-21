	@Test
	public void singleNullValue() {
		Object k1 = generateKey(new Object[] { null });
		Object k2 = generateKey(new Object[] { null });
		Object k3 = generateKey(new Object[] { "different" });
		assertThat(k1.hashCode(), equalTo(k2.hashCode()));
		assertThat(k1.hashCode(), not(equalTo(k3.hashCode())));
		assertThat(k1, equalTo(k2));
		assertThat(k1, not(equalTo(k3)));
		assertThat(k1, instanceOf(SimpleKey.class));
	}
