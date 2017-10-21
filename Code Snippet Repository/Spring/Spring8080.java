	@Test
	public void supportsXmlRootElement() throws Exception {
		marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(DummyRootElement.class, DummyType.class);
		marshaller.afterPropertiesSet();
		assertTrue("Jaxb2Marshaller does not support XmlRootElement class", marshaller.supports(DummyRootElement.class));
		assertTrue("Jaxb2Marshaller does not support XmlRootElement generic type", marshaller.supports((Type)DummyRootElement.class));

		assertFalse("Jaxb2Marshaller supports DummyType class", marshaller.supports(DummyType.class));
		assertFalse("Jaxb2Marshaller supports DummyType type", marshaller.supports((Type)DummyType.class));
	}
