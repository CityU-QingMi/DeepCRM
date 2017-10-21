	@Test
	public void readAndWriteAndIndexedReadAndIndexedWriteMethods() throws IntrospectionException {
		@SuppressWarnings("unused")
		class C {
			// read method
			public String[] getFoos() { return null; }
			// indexed read method
			public String getFoos(int i) { return null; }
			// write method
			public void setFoos(String[] foos) { }
			// indexed write method
			public void setFoos(int i, String foo) { }
		}

		BeanInfo bi = Introspector.getBeanInfo(C.class);
		BeanInfo ebi = new ExtendedBeanInfo(Introspector.getBeanInfo(C.class));

		assertThat(hasReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(bi, "foos"), is(true));
		assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
		assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(true));

		assertThat(hasReadMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasWriteMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(true));
	}
