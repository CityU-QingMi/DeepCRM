	@Test
	public void testRevisionsCounts() {
		Assert.assertEquals(
				Arrays.asList( 1, 2, 3 ), getAuditReader().getRevisions(
				EmbeddableMapEntity.class,
				eme1_id
		)
		);
		Assert.assertEquals(
				Arrays.asList( 1, 3 ), getAuditReader().getRevisions(
				EmbeddableMapEntity.class,
				eme2_id
		)
		);
	}
