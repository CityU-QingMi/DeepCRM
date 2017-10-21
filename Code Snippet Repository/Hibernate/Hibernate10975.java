	@Test
	public void testRevisionsCounts() {
		Assert.assertEquals(
				Arrays.asList( 1 ), getAuditReader().getRevisions(
				PersonTuple.class,
				tuple1Ver1.getPersonTupleId()
		)
		);
		Assert.assertEquals(
				Arrays.asList( 2, 3 ), getAuditReader().getRevisions(
				PersonTuple.class,
				tuple2Ver1.getPersonTupleId()
		)
		);
		Assert.assertEquals(
				Arrays.asList( 2, 4 ), getAuditReader().getRevisions(
				Person.class,
				personCVer1.getId()
		)
		);
	}
