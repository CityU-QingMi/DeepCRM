	public void testInheritance() throws Exception {

		// entity inheritance
		assertSuperClassRelationShipInMetamodel( Customer.class, User.class );


		// mapped super class
		assertSuperClassRelationShipInMetamodel( House.class, Building.class );
		assertSuperClassRelationShipInMetamodel( Building.class, Area.class );

		// METAGEN-29
		assertSuperClassRelationShipInMetamodel( Person.class, AbstractEntity.class );
		assertPresenceOfFieldInMetamodelFor( AbstractEntity.class, "id", "Property 'id' should exist" );
		assertPresenceOfFieldInMetamodelFor( AbstractEntity.class, "foo", "Property should exist - METAGEN-29" );
		assertAttributeTypeInMetaModelFor(
				AbstractEntity.class,
				"foo",
				Object.class,
				"Object is the upper bound of foo "
		);

		assertPresenceOfFieldInMetamodelFor( Person.class, "name", "Property 'name' should exist" );
	}
