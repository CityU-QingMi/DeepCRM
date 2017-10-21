	@Test
	public void testRevisionsCounts() {
		assertEquals(
				Arrays.asList( (Number) 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 ),
				getAuditReader()
						.getRevisions(
								PartialModifiedFlagsEntity.class,
								entityId
						)
		);
	}
