	@Test
	public void multipleValues()  {
		Object k1 = generateKey(new Object[] { "a", 1, "b" });
		Object k2 = generateKey(new Object[] { "a", 1, "b" });
		Object k3 = generateKey(new Object[] { "b", 1, "a" });
		assertThat(k1.hashCode(), equalTo(k2.hashCode()));
		assertThat(k1.hashCode(), not(equalTo(k3.hashCode())));
		assertThat(k1, equalTo(k2));
		assertThat(k1, not(equalTo(k3)));
	}
