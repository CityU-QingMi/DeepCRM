	public void testDeleteWithMetadataWhereFragments() throws Throwable {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		// Note: we are just checking the syntax here...
		s.createQuery("delete from Bar").executeUpdate();
		s.createQuery("delete from Bar where barString = 's'").executeUpdate();

		t.commit();
		s.close();
	}
