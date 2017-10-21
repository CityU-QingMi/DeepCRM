	@Test
	public void testHistoryOfId6() {

		DefaultValueComponentTestEntity ent1 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id6, 1
		);
		DefaultValueComponentTestEntity ent2 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id6, 2
		);

		log.error( "------------ id6 -------------" );
		log.error( ent1.toString() );
		log.error( ent2.toString() );

		DefaultValueComponentTestEntity expectedVer1 = DefaultValueComponentTestEntity
				.of( id6, DefaultValueComponent1.of( null, null ) );
		DefaultValueComponentTestEntity expectedVer2 = DefaultValueComponentTestEntity
				.of(
						id6, DefaultValueComponent1.of(
						null, DefaultValueComponent2
						.of( "upd-c2-str1", null )
				)
				);

		assert ent2.equals( expectedVer2 );
		assert ent1.equals( expectedVer1 );
	}
