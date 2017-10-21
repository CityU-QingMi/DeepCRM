	@Test
	public void testLoadOneToManyFetchProfile() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						session.enableFetchProfile( "offering.details" );
						CourseOffering section = ( CourseOffering ) session.get( CourseOffering.class, data.getSectionId() );
						assertEquals( 3, sessionFactory().getStatistics().getEntityLoadCount() ); // section + (enrollments + course)
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						assertTrue( Hibernate.isInitialized( section.getEnrollments() ) );
						session.getTransaction().commit();
						session.close();
					}
				}
		);
	}
