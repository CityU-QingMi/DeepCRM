	private void checkEntityNames() {
		String currCar1EN = getSession().getEntityName( currentCar1 );
		String currPerson1EN = getSession().getEntityName( currentPerson1 );

		String car1_1EN = getAuditReader().getEntityName( id_car1, 2, car1_1 );
		assert (currCar1EN.equals( car1_1EN ));

		String person1_1EN = getAuditReader().getEntityName( id_pers1, 1, person1_1 );
		assert (currPerson1EN.equals( person1_1EN ));
	}
