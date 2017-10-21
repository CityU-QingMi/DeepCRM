	@Test
	@TestForIssue(jiraKey = "")
	public void testParameterRegisterredCollection() {

		LongList longList = new LongList(5L, 11L, 6123L, -61235L, 24L);

		doInJPA( this::entityManagerFactory, em -> {
			SpecialItem item = new SpecialItem( "LongList", longList );
			em.persist( item );
		} );

		doInJPA( this::entityManagerFactory, em -> {

			SpecialItem item = (SpecialItem) em.createNativeQuery(
				"SELECT * FROM special_table WHERE longList = ?", SpecialItem.class )
			.setParameter(1, longList)
			.getSingleResult();

			assertEquals( "LongList", item.getName() );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			SpecialItem item = (SpecialItem) em.createNativeQuery(
				"SELECT * FROM special_table WHERE longList = :longList", SpecialItem.class )
			.setParameter("longList", longList)
			.getSingleResult();

			assertEquals( "LongList", item.getName() );
		} );
	}
