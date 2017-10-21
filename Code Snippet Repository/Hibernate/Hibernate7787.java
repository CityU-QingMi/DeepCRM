	@Test
	public void testLoadManyToOneFetchProfile() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						session.enableFetchProfile( "enrollment.details" );
						Enrollment enrollment = ( Enrollment ) session.get( Enrollment.class, data.getEnrollmentId() );
						assertEquals( 3, sessionFactory().getStatistics().getEntityLoadCount() ); // enrollment + (section + student)
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						assertTrue( Hibernate.isInitialized( enrollment.getOffering() ) );
						assertTrue( Hibernate.isInitialized( enrollment.getStudent() ) );
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						session.getTransaction().commit();
						session.close();
					}
				}
		);
	}
