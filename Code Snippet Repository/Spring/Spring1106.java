	@Test
	public void reproSpr8522() throws IntrospectionException {
		@SuppressWarnings("unused") class C {
			public Object setDateFormat(String pattern) { return new Object(); }
			public Object setDateFormat(int style) { return new Object(); }
			public Object setDateFormat(int dateStyle, int timeStyle) { return new Object(); }
		}
		BeanInfo bi = Introspector.getBeanInfo(C.class);

		assertThat(hasReadMethodForProperty(bi, "dateFormat"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "dateFormat"), is(false));
		assertThat(hasIndexedReadMethodForProperty(bi, "dateFormat"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(bi, "dateFormat"), is(false));

		BeanInfo ebi = new ExtendedBeanInfo(bi);

		assertThat(hasReadMethodForProperty(bi, "dateFormat"), is(false));
		assertThat(hasWriteMethodForProperty(bi, "dateFormat"), is(false));
		assertThat(hasIndexedReadMethodForProperty(bi, "dateFormat"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(bi, "dateFormat"), is(false));

		assertThat(hasReadMethodForProperty(ebi, "dateFormat"), is(false));
		assertThat(hasWriteMethodForProperty(ebi, "dateFormat"), is(true));
		assertThat(hasIndexedReadMethodForProperty(ebi, "dateFormat"), is(false));
		assertThat(hasIndexedWriteMethodForProperty(ebi, "dateFormat"), is(false));
	}
