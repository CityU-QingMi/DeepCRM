	@Test
	public void testRetrieveRevisionsWithEntityName() {
		List<Number> pers1Revs = getAuditReader().getRevisions( Person.class, "Personaje", id_pers1 );
		List<Number> pers2Revs = getAuditReader().getRevisions( Person.class, "Personaje", id_pers2 );
		List<Number> pers3Revs = getAuditReader().getRevisions( Person.class, "Personaje", id_pers3 );

		assert (pers1Revs.size() == 3);
		assert (pers2Revs.size() == 2);
		assert (pers3Revs.size() == 1);
	}
