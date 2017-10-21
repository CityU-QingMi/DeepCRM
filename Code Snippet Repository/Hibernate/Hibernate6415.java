	@Test
	public void testSortingElementCollectionSyntax() {
		Session session = openSession();
		session.beginTransaction();

		session.createQuery( "from Person p join fetch p.nickNamesAscendingNaturalSort" ).list();
		session.createQuery( "from Person p join fetch p.nickNamesDescendingNaturalSort" ).list();

		session.createQuery( "from Person p join fetch p.addressesAscendingNaturalSort" ).list();
		session.createQuery( "from Person p join fetch p.addressesDescendingNaturalSort" ).list();
		session.createQuery( "from Person p join fetch p.addressesCityAscendingSort" ).list();
		session.createQuery( "from Person p join fetch p.addressesCityDescendingSort" ).list();

		session.getTransaction().commit();
		session.close();
	}
