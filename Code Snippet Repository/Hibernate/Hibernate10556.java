	private void checkEntityNames() {
		String currPerson1EN = getSession().getEntityName( person1 );
		String currCar1EN = getSession().getEntityName( car1 );

		String person1_1EN = getAuditReader().getEntityName( id_pers1, 1, person1_1 );
		assert (currPerson1EN.equals( person1_1EN ));

		String car1_2EN = getAuditReader().getEntityName( id_car1, 2, car1_2 );
		assert (currCar1EN.equals( car1_2EN ));
	}
