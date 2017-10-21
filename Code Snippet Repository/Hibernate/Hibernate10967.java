	@Test
	public void testHistoryOfEd1() {
		ListJoinColumnBidirectionalRefIngEntity ing1 = getEntityManager().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		);
		ListJoinColumnBidirectionalRefIngEntity ing2 = getEntityManager().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id
		);

		ListJoinColumnBidirectionalRefEdEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed1_id,
				1
		);
		ListJoinColumnBidirectionalRefEdEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed1_id,
				2
		);
		ListJoinColumnBidirectionalRefEdEntity rev3 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed1_id,
				3
		);
		ListJoinColumnBidirectionalRefEdEntity rev4 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed1_id,
				4
		);

		assertTrue( rev1.getOwner().equals( ing1 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
		assertTrue( rev3.getOwner().equals( ing1 ) );
		assertTrue( rev4.getOwner().equals( ing2 ) );

		assertEquals( rev1.getData(), "ed1" );
		assertEquals( rev2.getData(), "ed1" );
		assertEquals( rev3.getData(), "ed1 bis" );
		assertEquals( rev4.getData(), "ed1 bis" );
	}
