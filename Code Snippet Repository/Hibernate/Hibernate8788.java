	@Test
	public void testGetProperty() {
		RootClass pc = new RootClass( metadataBuildingContext );
		Property p = new Property();
		p.setName("name");
		pc.addProperty(p);
		Assert.assertEquals(p, pc.getProperty("name"));
		Assert.assertEquals(p, pc.getProperty("name.test"));
		try {
			Assert.assertNull(pc.getProperty("test"));
			Assert.fail("MappingException expected");
		} catch (MappingException e) {
			// expected
		}
	}
