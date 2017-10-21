	@Test
	public void testLoadComponentDerefFetchProfile() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						session.enableFetchProfile( "course.details" );
						Course course = ( Course ) session.get( Course.class, data.getCourseId() );
						assertEquals( 2, sessionFactory().getStatistics().getEntityLoadCount() ); // course + department
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						assertTrue( Hibernate.isInitialized( course.getCode().getDepartment() ) );
						session.getTransaction().commit();
						session.close();
					}
				}
		);
	}
