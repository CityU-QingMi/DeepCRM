	@Test
	public void testForeignGenerator() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Owner owner = new Owner();
		OwnerAddress address = new OwnerAddress();
		owner.setAddress( address );
		address.setOwner( owner );
		s.persist( owner );
		s.flush();
		s.clear();
		owner = ( Owner ) s.get( Owner.class, owner.getId() );
		assertNotNull( owner );
		assertNotNull( owner.getAddress() );
		assertEquals( owner.getId(), owner.getAddress().getId() );
		tx.rollback();
		s.close();
	}
