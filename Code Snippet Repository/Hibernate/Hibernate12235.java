	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses({ Address.class, AddressImpl.class, House.class })
	public void testEmbeddableWithTargetAnnotation() {
		assertMetamodelClassGeneratedFor( House.class );
		assertPresenceOfFieldInMetamodelFor( House.class, "address", "the metamodel should have a member 'address'" );
		assertAttributeTypeInMetaModelFor(
				House.class,
				"address",
				AddressImpl.class,
				"The target annotation set the type to AddressImpl"
		);
	}
