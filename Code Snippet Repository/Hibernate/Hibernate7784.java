	@Test
	public void testNormalLoading() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						CourseOffering section = ( CourseOffering ) session.get( CourseOffering.class, data.getSectionId() );
						assertEquals( 1, sessionFactory().getStatistics().getEntityLoadCount() );
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						assertFalse( Hibernate.isInitialized( section.getCourse() ) );
						assertFalse( Hibernate.isInitialized( section.getEnrollments() ) );
						assertFalse( Hibernate.isInitialized( section.getCourse().getCode().getDepartment() ) );
						assertTrue( Hibernate.isInitialized( section.getCourse() ) );
						assertEquals( 1, sessionFactory().getStatistics().getEntityFetchCount() );
						session.getTransaction().commit();
						session.close();
					}
				}
		);
	}
