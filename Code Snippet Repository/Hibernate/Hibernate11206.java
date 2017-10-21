	@Test
	public void testTrackDeletedEntities() {
		ModifiedEntityTypeEntity steDescriptor = new ModifiedEntityTypeEntity( StrTestEntity.class.getName() );
		ModifiedEntityTypeEntity siteDescriptor = new ModifiedEntityTypeEntity( StrIntTestEntity.class.getName() );

		CustomTrackingRevisionEntity ctre = getAuditReader().findRevision( CustomTrackingRevisionEntity.class, 3 );

		assert ctre.getModifiedEntityTypes() != null;
		assert ctre.getModifiedEntityTypes().size() == 2;
		assert TestTools.makeSet( steDescriptor, siteDescriptor ).equals( ctre.getModifiedEntityTypes() );
	}
