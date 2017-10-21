	@Test
	public void testObtainEntityNameAuditedEntityWithEntityName() {
		person1_1 = getAuditReader().find( Person.class, "Personaje", id_pers1, 1 );
		person1_2 = getAuditReader().find( Person.class, "Personaje", id_pers1, 2 );
		person1_3 = getAuditReader().find( Person.class, "Personaje", id_pers1, 3 );

		String currentPers1EN = getSession().getEntityName( currentPers1 );

		String person1EN = getAuditReader().getEntityName( person1_1.getId(), 1, person1_1 );
		assert (currentPers1EN.equals( person1EN ));

		String person2EN = getAuditReader().getEntityName( person1_2.getId(), 2, person1_2 );
		assert (currentPers1EN.equals( person2EN ));

		String person3EN = getAuditReader().getEntityName( person1_3.getId(), 3, person1_3 );
		assert (currentPers1EN.equals( person3EN ));

	}
