	@Test
	public void testJdkEnumStyleEnumConstant() throws Exception {
		Session s = openSession();
		s.beginTransaction();

		s.createQuery( "from Zoo z where z.classification = org.hibernate.test.hql.Classification.LAME" ).list();

		s.getTransaction().commit();
		s.close();
	}
