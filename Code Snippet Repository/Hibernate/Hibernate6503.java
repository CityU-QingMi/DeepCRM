	@Test
	@TestForIssue(jiraKey = "")
	public void testTransientMergeComponentParent() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Book b = new Book();
		b.setIsbn( UUID.randomUUID().toString() );
		b.setSummary( new Summary() );
		b = (Book) s.merge( b );
		tx.commit();
		s.close();
	}
