	@Test
	public void testLoadDeepFetchProfile() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						// enable both enrollment and offering detail profiles;
						// then loading the section/offering should fetch the enrollment
						// which in turn should fetch student (+ offering).
						session.enableFetchProfile( "offering.details" );
						session.enableFetchProfile( "enrollment.details" );
						CourseOffering section = ( CourseOffering ) session.get( CourseOffering.class, data.getSectionId() );
						assertEquals( 4, sessionFactory().getStatistics().getEntityLoadCount() ); // section + (course + enrollments + (student))
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						assertTrue( Hibernate.isInitialized( section.getEnrollments() ) );
						session.getTransaction().commit();
						session.close();
					}
				}
		);
	}
