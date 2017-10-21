	@Test
	public void testRevisionHistory() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			assertEquals( Arrays.asList( 1, 2, 3 ), getAuditReader().getRevisions( A.class, a.getId() ) );

			assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( B.class, b1.getId() ) );
			assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( C.class, c1.getId() ) );

			assertEquals( Arrays.asList( 2 ), getAuditReader().getRevisions( B.class, b2.getId() ) );
			assertEquals( Arrays.asList( 2 ), getAuditReader().getRevisions( C.class, c2.getId() ) );
		} );
	}
