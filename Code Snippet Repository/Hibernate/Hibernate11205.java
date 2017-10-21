	@Test
	public void testTrackModifiedEntities() {
		ModifiedEntityTypeEntity siteDescriptor = new ModifiedEntityTypeEntity( StrIntTestEntity.class.getName() );

		CustomTrackingRevisionEntity ctre = getAuditReader().findRevision( CustomTrackingRevisionEntity.class, 2 );

		assert ctre.getModifiedEntityTypes() != null;
		assert ctre.getModifiedEntityTypes().size() == 1;
		assert TestTools.makeSet( siteDescriptor ).equals( ctre.getModifiedEntityTypes() );
	}
