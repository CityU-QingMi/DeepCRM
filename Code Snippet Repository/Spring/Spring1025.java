	@Test
	public void testSPR6063() {
		PropertyDescriptor[] descrs = BeanUtils.getPropertyDescriptors(Bean.class);

		PropertyDescriptor keyDescr = BeanUtils.getPropertyDescriptor(Bean.class, "value");
		assertEquals(String.class, keyDescr.getPropertyType());
		for (PropertyDescriptor propertyDescriptor : descrs) {
			if (propertyDescriptor.getName().equals(keyDescr.getName())) {
				assertEquals(propertyDescriptor.getName() + " has unexpected type",
						keyDescr.getPropertyType(), propertyDescriptor.getPropertyType());
			}
		}
	}
