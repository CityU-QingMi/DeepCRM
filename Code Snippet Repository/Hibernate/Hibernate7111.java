	private int createAAndB() {
		Session s = openSession();
		s.getTransaction().begin();
		B b = new B();
		b.setIdPart1( currentId );
		b.setIdPart2( currentId);
		b.setOtherProperty("foo");
		s.save( b );

		A a = new A();
		a.setId( currentId );
		a.setB( b );

		s.save( a );

		s.getTransaction().commit();
		s.close();

		currentId++;

		return currentId - 1;
	}
