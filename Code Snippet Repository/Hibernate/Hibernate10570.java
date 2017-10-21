	private void loadDataOnSessionAndAuditReader() {
		currentPerson1 = (Person) getSession().get( "Personaje", id_pers1 );
		person2 = (Person) getSession().get( "Personaje", id_pers2 );

		currentCar1 = (Car) getSession().get( Car.class, id_car1 );

		car1 = getAuditReader().find( Car.class, id_car1, 1 );
		car2 = getAuditReader().find( Car.class, id_car2, 2 );

	}
