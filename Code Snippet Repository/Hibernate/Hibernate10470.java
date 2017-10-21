	@Test
	public void testHistoryOfId1() {

		DefaultValueComponentTestEntity ent1 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id1, 1
		);
		DefaultValueComponentTestEntity ent2 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id1, 2
		);

		log.error( "------------ id1 -------------" );
		log.error( ent1.toString() );
		log.error( ent2.toString() );

		checkCorrectlyPersisted( id1, null, "upd-c2-str1" );

		DefaultValueComponentTestEntity expectedVer1 = DefaultValueComponentTestEntity
				.of( id1, DefaultValueComponent1.of( "c1-str1", null ) );
		DefaultValueComponentTestEntity expectedVer2 = DefaultValueComponentTestEntity
				.of(
						id1, DefaultValueComponent1.of(
						null, DefaultValueComponent2
						.of( "upd-c2-str1", "upd-c2-str2" )
				)
				);

		assert ent2.equals( expectedVer2 );
		assert ent1.equals( expectedVer1 );
	}
