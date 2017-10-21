	@Test
	public void testHistoryOfId4() {

		DefaultValueComponentTestEntity ent1 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id4, 1
		);
		DefaultValueComponentTestEntity ent2 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id4, 2
		);

		log.error( "------------ id4 -------------" );
		log.error( ent1.toString() );
		log.error( ent2.toString() );

		DefaultValueComponentTestEntity expectedVer1 = DefaultValueComponentTestEntity
				.of(
						id4, DefaultValueComponent1.of(
						null, DefaultValueComponent2
						.of( null, "c2-str2" )
				)
				);
		DefaultValueComponentTestEntity expectedVer2 = DefaultValueComponentTestEntity
				.of(
						id4, DefaultValueComponent1.of(
						null, DefaultValueComponent2
						.of( "upd-c2-str1", "c2-str2" )
				)
				);

		assert ent1.equals( expectedVer1 );
		assert ent2.equals( expectedVer2 );
	}
