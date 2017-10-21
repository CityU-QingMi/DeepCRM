	@Test
	@WithClasses({ Person.class, XmlPerson.class, PersonId.class })
	@WithMappingFiles("")
	public void testGeneratedAnnotationNotGenerated() {
		assertMetamodelClassGeneratedFor( Person.class );
		assertPresenceOfFieldInMetamodelFor(
				Person.class, "id", "Property id should be in metamodel"
		);

		assertPresenceOfFieldInMetamodelFor(
				Person.class, "address", "Property id should be in metamodel"
		);

		assertPresenceOfFieldInMetamodelFor(
				XmlPerson.class, "id", "Property id should be in metamodel"
		);

		assertPresenceOfFieldInMetamodelFor(
				XmlPerson.class, "address", "Property id should be in metamodel"
		);
	}
