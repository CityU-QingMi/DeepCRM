	@Test
	@TestForIssue(jiraKey = "")
	@WithClasses({ JetPlane.class, PersistenceBase.class, Plane.class })
	public void testDeepInheritance() throws Exception {
		assertMetamodelClassGeneratedFor( Plane.class );
		assertMetamodelClassGeneratedFor( JetPlane.class );
		assertPresenceOfFieldInMetamodelFor( JetPlane.class, "jets" );
		assertAttributeTypeInMetaModelFor(
				JetPlane.class,
				"jets",
				Integer.class,
				"jets should be defined in JetPlane_"
		);
	}
