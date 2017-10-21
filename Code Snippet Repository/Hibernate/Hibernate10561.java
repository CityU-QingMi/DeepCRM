	private void loadDataOnSessionAndAuditReader() {

		currentCar1 = (Car) getSession().get( Car.class, id_car1 );
		currentPerson1 = (Person) getSession().get( "Personaje", id_pers1 );

		car1_1 = getAuditReader().find( Car.class, id_car1, 2 );
		Car car2 = getAuditReader().find( Car.class, id_car2, 2 );

		for ( Person owner : car1_1.getOwners() ) {
			owner.getName();
			owner.getAge();
		}
		for ( Person owner : car2.getOwners() ) {
			owner.getName();
			owner.getAge();
		}
	}
