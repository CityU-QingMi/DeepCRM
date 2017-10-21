	@Test
	public void indexedReadAndNonStandardWriteAndNonStandardIndexedWrite() throws IntrospectionException {
		@SuppressWarnings("unused")
		class C {
			// non-standard write method
			public C setFoos(String[] foos) { return this; }
			// indexed read method
			public String getFoos(int i) { return null; }
			// non-standard indexed write method
			public C setFoos(int i, String foo) { return this; }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);

		assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foos"), is(false));
		// again as above, standard Inspector picks up non-void return types on indexed write methods by default
		assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(false));

		BeanInfo ebi = new ExtendedBeanInfo(Introspector.getBeanInfo(C.class));

		assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foos"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(false));

		assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(true));
	}
