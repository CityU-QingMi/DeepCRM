	@Test
	public void testRetrieveAuditedEntityWithEntityName() {
		person1_1 = getAuditReader().find( Person.class, "Personaje", id_pers1, 1 );
		person1_2 = getAuditReader().find( Person.class, "Personaje", id_pers1, 2 );
		person1_3 = getAuditReader().find( Person.class, "Personaje", id_pers1, 3 );

		assert (person1_1 != null);
		assert (person1_2 != null);
		assert (person1_3 != null);

	}
