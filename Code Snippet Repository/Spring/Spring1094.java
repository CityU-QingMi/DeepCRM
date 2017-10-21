	@Test
	public void indexedWriteMethodOnly() throws IntrospectionException {
		@SuppressWarnings("unused")
		class C {
			// indexed write method
			public void setFoos(int i, String foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(Introspector.getBeanInfo(C.class));

		assertThat(hasWriteMethodForProperty(bi, "foos"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(true));

		assertThat(hasWriteMethodForProperty(ebi, "foos"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(true));
	}
