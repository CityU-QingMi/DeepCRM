	@Test
	public void testAcceptsUnresolvedPropertyTypesIfATargetEntityIsExplicitlySet() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Gene item = new Gene();
		s.persist( item );
		s.flush();
		tx.rollback();
		s.close();
	}
