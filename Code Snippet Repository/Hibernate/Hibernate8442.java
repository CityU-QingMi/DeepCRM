	@Test
	@TestForIssue(jiraKey = "")
	public void testTreatWithRestrictionOnAbstractClass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Greyhound greyhound = new Greyhound();
		Dachshund dachshund = new Dachshund();
		s.save( greyhound );
		s.save( dachshund );

		List results = s.createQuery( "select treat (a as Dog) from Animal a where a.fast = TRUE" ).list();

		assertEquals( Arrays.asList( greyhound ), results );

		tx.commit();
		s.close();
	}
