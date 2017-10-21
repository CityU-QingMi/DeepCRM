	@Test
	public void testAcceptsUnresolvedPropertyTypesIfATypeExplicitlySet() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Gene item = new Gene();
		item.setState( State.DORMANT );
		s.persist( item );
		s.flush();
		s.clear();
		item = (Gene) s.get( Gene.class, item.getId() );
		assertEquals( State.DORMANT, item.getState() );
		tx.rollback();
		s.close();
	}
