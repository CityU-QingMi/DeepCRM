	private void checkEntityNames() {
		String currentCar1EN = getSession().getEntityName( currentCar1 );

		String currentPerson1EN = getSession().getEntityName( currentPerson1 );

		String car1EN = getAuditReader().getEntityName( id_car1, 1, car1 );
		assert (currentCar1EN.equals( car1EN ));

		String person1EN = getAuditReader().getEntityName( id_pers1, 1, person1 );
		assert (currentPerson1EN.equals( person1EN ));
	}
