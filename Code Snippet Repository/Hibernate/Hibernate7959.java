	private void destroyTestBaseData() {
		Session session = openSession();
		Transaction txn = session.beginTransaction();

		for ( Long createdAnimalId : createdAnimalIds ) {
			Animal animal = session.load( Animal.class, createdAnimalId );
			session.delete( animal );
		}

		txn.commit();
		session.close();

		createdAnimalIds.clear();
	}
