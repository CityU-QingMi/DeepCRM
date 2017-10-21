	@Test
	public void propertyGenericClassList() throws Exception {
		IntegerClass genericBean = new IntegerClass();
		Property property = new Property(genericBean.getClass(), genericBean.getClass().getMethod("getListProperty"),
				genericBean.getClass().getMethod("setListProperty", List.class));
		TypeDescriptor desc = new TypeDescriptor(property);
		assertEquals(List.class, desc.getType());
		assertEquals(Integer.class, desc.getElementTypeDescriptor().getType());
		assertNotNull(desc.getAnnotation(MethodAnnotation1.class));
		assertTrue(desc.hasAnnotation(MethodAnnotation1.class));
	}
