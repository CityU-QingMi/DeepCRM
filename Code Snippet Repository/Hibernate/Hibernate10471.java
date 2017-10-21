	@Test
	public void testHistoryOfId2() {

		DefaultValueComponentTestEntity ent1 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id2, 1
		);
		DefaultValueComponentTestEntity ent2 = getAuditReader().find(
				DefaultValueComponentTestEntity.class, id2, 2
		);

		log.error( "------------ id2 -------------" );
		log.error( ent1.toString() );
		log.error( ent2.toString() );

		DefaultValueComponentTestEntity expectedVer1 = DefaultValueComponentTestEntity
				.of(
						id2, DefaultValueComponent1.of(
						"c1-str1",
						DefaultValueComponent2.of( "c2-str1", "c2-str2" )
				)
				);
		DefaultValueComponentTestEntity expectedVer2 = DefaultValueComponentTestEntity
				.of(
						id2, DefaultValueComponent1.of(
						"c1-str1",
						DefaultValueComponent2.of( "upd-c2-str1", "c2-str2" )
				)
				);

		assert ent1.equals( expectedVer1 );
		assert ent2.equals( expectedVer2 );
	}
