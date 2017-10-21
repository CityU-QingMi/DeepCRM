	@Override
	protected void prepareTest() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			final Student student = new Student();
			student.setId( STUDENT_ID );
			student.setName( "dre" );
			student.setStatus( "active" );
			student.setAge( 21 );
			student.setAddress( new Address( "London", "Lollard St" ) );
			session.save( student );

			final Student student2 = new Student();
			student2.setId( new Identifier( 4, new Identifier2( 4, 6L ) ) );
			student2.setName( "Livia" );
			student2.setStatus( "active" );
			student2.setAge( 27 );
			student2.setAddress( new Address( "London", "Oxford St" ) );
			session.save( student2 );
	   });
	}
