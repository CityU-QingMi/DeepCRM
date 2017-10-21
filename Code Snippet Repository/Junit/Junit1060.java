	@Test
	void serialization() throws Exception {
		TestIdentifier identifier = serializeAndDeserialize(//
			new TestIdentifier("uniqueId", "displayName", ClassSource.from(TestIdentifierTests.class),
				singleton(TestTag.create("aTag")), TestDescriptor.Type.TEST, "parentId", "reportingName"));

		assertEquals("uniqueId", identifier.getUniqueId());
		assertEquals("displayName", identifier.getDisplayName());
		assertEquals("reportingName", identifier.getLegacyReportingName());
		assertThat(identifier.getSource()).contains(ClassSource.from(TestIdentifierTests.class));
		assertEquals(singleton(TestTag.create("aTag")), identifier.getTags());
		assertEquals(TestDescriptor.Type.TEST, identifier.getType());
		assertTrue(identifier.isTest());
		assertFalse(identifier.isContainer());
		assertThat(identifier.getParentId()).contains("parentId");
	}
