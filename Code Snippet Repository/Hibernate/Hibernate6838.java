	@Test
	public void testMappingProperties() {
        log.warn("Commented out test");

		ClassMetadata metaData = sessionFactory().getClassMetadata(
				NaturalIdOnManyToOne.class
		);
		assertTrue(
				"Class should have a natural key", metaData
						.hasNaturalIdentifier()
		);
		int[] propertiesIndex = metaData.getNaturalIdentifierProperties();
		assertTrue( "Wrong number of elements", propertiesIndex.length == 1 );
	}
