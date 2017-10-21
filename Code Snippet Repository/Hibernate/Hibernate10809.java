	@Test
	public void testHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				StringMapEntity.class, sme1_id,
				"strings"
		);
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged(
				StringMapEntity.class, sme2_id,
				"strings"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				StringMapEntity.class, sme1_id,
				"strings"
		);
		assertEquals( 0, list.size() );

		list = queryForPropertyHasNotChanged(
				StringMapEntity.class, sme2_id,
				"strings"
		);
		assertEquals( 0, list.size() ); // in rev 2 there was no version generated for sme2_id
	}
