	private void loadDataOnSessionAndAuditReader() {

		car1_2 = getAuditReader().find( Car.class, id_car1, 2 );
		Car car2_2 = getAuditReader().find( Car.class, id_car2, 2 );

		// navigate through relations to load objects
		for ( Person owner : car1_2.getOwners() ) {
			for ( Car ownedCar : owner.getCars() ) {
				ownedCar.getRegistrationNumber();
			}
		}
		for ( Person owner : car2_2.getOwners() ) {
			for ( Car ownedCar : owner.getCars() ) {
				ownedCar.getRegistrationNumber();
			}
		}

		car1 = (Car) getSession().get( Car.class, id_car1 );
		person1 = (Person) getSession().get( "Personaje", id_pers1 );
		person1_1 = getAuditReader().find( Person.class, "Personaje", id_pers1, 1 );
	}
