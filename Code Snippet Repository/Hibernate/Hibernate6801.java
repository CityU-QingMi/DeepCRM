	@Test
	public void testTwoManyToOneNonPk() throws Exception {
		//2 many to one non pk pointing to the same referencedColumnName should not fail
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		org.hibernate.test.annotations.manytoone.Customer customer = new org.hibernate.test.annotations.manytoone.Customer();
		customer.userId="123";
		org.hibernate.test.annotations.manytoone.Customer customer2 = new org.hibernate.test.annotations.manytoone.Customer();
		customer2.userId="124";
		s.persist( customer2 );
		s.persist( customer );
		Deal deal = new Deal();
		deal.from = customer;
		deal.to = customer2;
		s.persist( deal );
		s.flush();
		s.clear();
		deal = (Deal) s.get( Deal.class, deal.id );
		assertNotNull( deal.from );
		assertNotNull( deal.to );
		tx.rollback();
		s.close();
	}
