	@Test
	public void testHistoryOfId5() {

		DefaultValueComponentTestEntity ent1 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id5, 1
		);
		DefaultValueComponentTestEntity ent2 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id5, 2
		);

		log.error( "------------ id5 -------------" );
		log.error( ent1.toString() );
		log.error( ent2.toString() );

		DefaultValueComponentTestEntity expectedVer1 = DefaultValueComponentTestEntity
				.of(
						id5, DefaultValueComponent1.of(
						null, DefaultValueComponent2
						.of( "c2-str1", null )
				)
				);
		DefaultValueComponentTestEntity expectedVer2 = DefaultValueComponentTestEntity
				.of(
						id5, DefaultValueComponent1.of(
						null, DefaultValueComponent2
						.of( "upd-c2-str1", null )
				)
				);

		assert ent1.equals( expectedVer1 );
		assert ent2.equals( expectedVer2 );
	}
