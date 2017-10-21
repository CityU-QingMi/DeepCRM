	@Test
	public void testBeanPropertyIsArray() {
		PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(ContainerBean.class);
		for (PropertyDescriptor descriptor : descriptors) {
			if ("containedBeans".equals(descriptor.getName())) {
				assertTrue("Property should be an array", descriptor.getPropertyType().isArray());
				assertEquals(descriptor.getPropertyType().getComponentType(), ContainedBean.class);
			}
		}
	}
