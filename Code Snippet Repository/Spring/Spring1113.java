	@Test
	public void shouldSupportStaticWriteMethod() throws IntrospectionException {
		{
			BeanInfo bi = Introspector.getBeanInfo(WithStaticWriteMethod.class);
			assertThat(hasReadMethodForProperty(bi, "prop1"), is(false));
			assertThat(hasWriteMethodForProperty(bi, "prop1"), is(false));
			assertThat(hasIndexedReadMethodForProperty(bi, "prop1"), is(false));
			assertThat(hasIndexedWriteMethodForProperty(bi, "prop1"), is(false));
		}
		{
			BeanInfo bi = new ExtendedBeanInfo(Introspector.getBeanInfo(WithStaticWriteMethod.class));
			assertThat(hasReadMethodForProperty(bi, "prop1"), is(false));
			assertThat(hasWriteMethodForProperty(bi, "prop1"), is(true));
			assertThat(hasIndexedReadMethodForProperty(bi, "prop1"), is(false));
			assertThat(hasIndexedWriteMethodForProperty(bi, "prop1"), is(false));
		}
	}
