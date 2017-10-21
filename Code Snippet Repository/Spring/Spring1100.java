	@Test
	public void cornerSpr9702() throws IntrospectionException {
		{ // baseline with standard write method
			@SuppressWarnings("unused")
			class C {
				// VOID-RETURNING, NON-INDEXED write method
				public void setFoos(String[] foos) { }
				// indexed read method
				public String getFoos(int i) { return null; }
			}

			BeanInfo bi = Introspector.getBeanInfo(C.class);
			assertThat(hasReadMethodForProperty(bi, "foos"), is(false));
			assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
			assertThat(hasWriteMethodForProperty(bi, "foos"), is(true));
			assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(false));

			BeanInfo ebi = Introspector.getBeanInfo(C.class);
			assertThat(hasReadMethodForProperty(ebi, "foos"), is(false));
			assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
			assertThat(hasWriteMethodForProperty(ebi, "foos"), is(true));
			assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(false));
		}
		{ // variant with non-standard write method
			@SuppressWarnings("unused")
			class C {
				// NON-VOID-RETURNING, NON-INDEXED write method
				public C setFoos(String[] foos) { return this; }
				// indexed read method
				public String getFoos(int i) { return null; }
			}

			BeanInfo bi = Introspector.getBeanInfo(C.class);
			assertThat(hasReadMethodForProperty(bi, "foos"), is(false));
			assertThat(hasIndexedReadMethodForProperty(bi, "foos"), is(true));
			assertThat(hasWriteMethodForProperty(bi, "foos"), is(false));
			assertThat(hasIndexedWriteMethodForProperty(bi, "foos"), is(false));

			BeanInfo ebi = new ExtendedBeanInfo(Introspector.getBeanInfo(C.class));
			assertThat(hasReadMethodForProperty(ebi, "foos"), is(false));
			assertThat(hasIndexedReadMethodForProperty(ebi, "foos"), is(true));
			assertThat(hasWriteMethodForProperty(ebi, "foos"), is(true));
			assertThat(hasIndexedWriteMethodForProperty(ebi, "foos"), is(false));
		}
	}
