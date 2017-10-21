	@Test
	public void testCanUseCriteriaQuery() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Store fnac = new Store();
		fnac.setName( "Fnac" );
		Supplier emi = new Supplier();
		emi.setName( "Emmanuel" );
		emi.setSuppStores( new HashSet<Store>() );
		fnac.setSuppliers( new HashSet<Supplier>() );
		fnac.getSuppliers().add( emi );
		emi.getSuppStores().add( fnac );
		s.persist( fnac );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		List result = s.createCriteria( Supplier.class ).createAlias( "suppStores", "s" ).add(
				Restrictions.eq( "s.name", "Fnac" ) ).list();
		assertEquals( 1, result.size() );
		tx.commit();
		s.close();
	}
