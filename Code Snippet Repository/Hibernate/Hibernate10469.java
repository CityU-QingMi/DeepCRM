	@Test
	public void testHistoryOfId0() {

		DefaultValueComponentTestEntity ent1 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id0, 1
		);
		DefaultValueComponentTestEntity ent2 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id0, 2
		);

		log.error( "------------ id0 -------------" );
		log.error( ent1.toString() );
		log.error( ent2.toString() );

		checkCorrectlyPersisted( id0, null, null );

		DefaultValueComponentTestEntity expectedVer1 = DefaultValueComponentTestEntity
				.of( id0, DefaultValueComponent1.of( null, null ) );
		DefaultValueComponentTestEntity expectedVer2 = DefaultValueComponentTestEntity
				.of( id0, DefaultValueComponent1.of( "upd-c1-str1", null ) );

		assert ent1.equals( expectedVer1 );
		assert ent2.equals( expectedVer2 );
	}
