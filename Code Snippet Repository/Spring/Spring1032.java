	@Test
	public void testCopyPropertiesWithInvalidProperty() {
		InvalidProperty source = new InvalidProperty();
		source.setName("name");
		source.setFlag1(true);
		source.setFlag2(true);
		InvalidProperty target = new InvalidProperty();
		BeanUtils.copyProperties(source, target);
		assertEquals(target.getName(), "name");
		assertTrue(target.getFlag1());
		assertTrue(target.getFlag2());
	}
