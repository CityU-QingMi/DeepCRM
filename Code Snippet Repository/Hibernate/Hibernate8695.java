	@Test
	public void testSimpleNestedCompositeAttributeProcessing() throws Exception {
		// create some test data
		Session session = openSession();
		session.beginTransaction();
		Person person = new Person();
		person.id = 1;
		person.name = "Joe Blow";
		person.address = new Address();
		person.address.address1 = "1313 Mockingbird Lane";
		person.address.city = "Pleasantville";
		person.address.country = "USA";
		AddressType addressType = new AddressType();
		addressType.typeName = "snail mail";
		person.address.type = addressType;
		session.save( person );
		session.getTransaction().commit();
		session.close();

//		session = openSession();
//		session.beginTransaction();
//		Person personGotten = (Person) session.get( Person.class, person.id );
//		assertEquals( person.id, personGotten.id );
//		assertEquals( person.address.address1, personGotten.address.address1 );
//		assertEquals( person.address.city, personGotten.address.city );
//		assertEquals( person.address.country, personGotten.address.country );
//		assertEquals( person.address.type.typeName, personGotten.address.type.typeName );
//		session.getTransaction().commit();
//		session.close();

		List results = getResults( sessionFactory().getEntityPersister( Person.class.getName() ) );
		assertEquals( 1, results.size() );
		Object result = results.get( 0 );
		assertNotNull( result );

		Person personWork = ExtraAssertions.assertTyping( Person.class, result );
		assertEquals( person.id, personWork.id );
		assertEquals( person.address.address1, personWork.address.address1 );
		assertEquals( person.address.city, personWork.address.city );
		assertEquals( person.address.country, personWork.address.country );
		assertEquals( person.address.type.typeName, person.address.type.typeName );

		// clean up test data
		session = openSession();
		session.beginTransaction();
		session.createQuery( "delete Person" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
