	@Test
	public void testComponentJoins() {
		// Just checking proper query construction and syntax checking via database query parser...
		Session session = openSession();
		session.beginTransaction();
		// use it in WHERE
		session.createQuery( "select p from Person p join p.name as n where n.lastName like '%'" ).list();
		// use it in SELECT
		session.createQuery( "select n.lastName from Person p join p.name as n" ).list();
		session.createQuery( "select n from Person p join p.name as n" ).list();
		// use it in ORDER BY
		session.createQuery( "select n from Person p join p.name as n order by n.lastName" ).list();
		session.createQuery( "select n from Person p join p.name as n order by p" ).list();
		session.createQuery( "select n from Person p join p.name as n order by n" ).list();
		session.getTransaction().commit();
		session.close();
	}
