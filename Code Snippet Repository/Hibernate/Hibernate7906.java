	@Test
	public void testExplicitToOneInnerJoinAndImplicitToOne() {
		final Employee employee1 = new Employee();
		employee1.setFirstName( "Jane" );
		employee1.setLastName( "Doe" );
		final Title title1 = new Title();
		title1.setDescription( "Jane's description" );
		final Department dept1 = new Department();
		dept1.setDeptName( "Jane's department" );
		employee1.setTitle( title1 );
		employee1.setDepartment( dept1 );

		final Employee employee2 = new Employee();
		employee2.setFirstName( "John" );
		employee2.setLastName( "Doe" );
		final Title title2 = new Title();
		title2.setDescription( "John's title" );
		employee2.setTitle( title2 );

		Session s = openSession();
		s.getTransaction().begin();
		s.persist( title1 );
		s.persist( dept1 );
		s.persist( employee1 );
		s.persist( title2 );
		s.persist( employee2 );
		s.getTransaction().commit();
		s.close();
		s = openSession();
		s.getTransaction().begin();
		Object[] result = (Object[]) s.createQuery(
				"select e.firstName, e.lastName, e.title.description, e.department from Employee e inner join e.department"
		).uniqueResult();
		assertEquals( employee1.getFirstName(), result[0] );
		assertEquals( employee1.getLastName(), result[1] );
		assertEquals( employee1.getTitle().getDescription(), result[2] );
		assertEquals( employee1.getDepartment().getDeptName(), ( (Department) result[3] ).getDeptName() );
		s.delete( employee1 );
		s.delete( title1 );
		s.delete( result[3] );
		s.delete( employee2 );
		s.delete( title2 );
		s.getTransaction().commit();
		s.close();
	}
