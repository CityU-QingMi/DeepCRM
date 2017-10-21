	@Override
	protected void prepareTest() {
		Session s = openSession();
		s.getTransaction().begin();
		BlogEntry blogEntry = new BlogEntry();
		blogEntry.setDetail( "detail" );
		blogEntry.setReportedBy( "John Doe" );
		s.persist( blogEntry );
		s.getTransaction().commit();
		s.close();

		blogEntryId = blogEntry.getId();
	}
