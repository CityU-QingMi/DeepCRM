	@Test
	public void propertyDescriptorOrderIsEqual() throws IntrospectionException {
		BeanInfo bi = Introspector.getBeanInfo(TestBean.class);
		BeanInfo ebi = new ExtendedBeanInfo(bi);

		for (int i = 0; i < bi.getPropertyDescriptors().length; i++) {
			assertThat("element " + i + " in BeanInfo and ExtendedBeanInfo propertyDescriptor arrays do not match",
					ebi.getPropertyDescriptors()[i].getName(), equalTo(bi.getPropertyDescriptors()[i].getName()));
		}
	}
