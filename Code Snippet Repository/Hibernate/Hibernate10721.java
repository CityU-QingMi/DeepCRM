	@Test
	public void testRevisionsCounts() {
		// Although it would seem that when modifying references both entities should be marked as modified, because
		// ownly the owning side is notified (because of the bi-owning mapping), a revision is created only for
		// the entity where the collection was directly modified.

		assertEquals(
				Arrays.asList( 1, 2, 3, 5 ), getAuditReader().getRevisions(
				ListBiowning1Entity.class,
				o1_1_id
		)
		);
		assertEquals( Arrays.asList( 1, 2, 5 ), getAuditReader().getRevisions( ListBiowning1Entity.class, o1_2_id ) );

		assertEquals( Arrays.asList( 1, 4 ), getAuditReader().getRevisions( ListBiowning2Entity.class, o2_1_id ) );
		assertEquals( Arrays.asList( 1, 4 ), getAuditReader().getRevisions( ListBiowning2Entity.class, o2_2_id ) );
	}
