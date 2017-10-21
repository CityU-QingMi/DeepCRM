	@Test
	public void indexedReadAndIndexedWriteMethods() throws IntrospectionException {
		@SuppressWarnings("unused")
		class C {
			// indexed read method
			public String getFoos(int i) { return null; }
			// indexed write method
			public void setFoos(int i, String foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(Introspector.getBeanInfo(C.class));

		assertThat(hasReadMethodForProperty(bi, "foos"), is(false));
		assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foos"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(true));

		assertThat(hasReadMethodForProperty(ebi, "foos"), is(false));
		assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foos"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(true));
	}
