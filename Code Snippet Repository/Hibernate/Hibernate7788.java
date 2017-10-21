	@Test
	public void testCriteriaManyToOneFetchProfile() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						session.enableFetchProfile( "enrollment.details" );
						Enrollment enrollment = ( Enrollment ) session.createCriteria( Enrollment.class ).uniqueResult();
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
