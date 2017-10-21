	@Test
	public void testHQL() {
		performWithStandardData(
				new TestCode() {
					public void perform(TestData data) {
						Session session = openSession();
						session.beginTransaction();
						session.enableFetchProfile( "offering.details" );
						session.enableFetchProfile( "enrollment.details" );
						List sections = session.createQuery( "from CourseOffering" ).list();
						int sectionCount = sections.size();
						assertEquals( "unexpected CourseOffering count", 1, sectionCount );
						assertEquals( 1, sessionFactory().getStatistics().getEntityLoadCount() );
						assertEquals( 0, sessionFactory().getStatistics().getEntityFetchCount() );
						session.getTransaction().commit();
						session.close();
					}
				}
		);
	}
