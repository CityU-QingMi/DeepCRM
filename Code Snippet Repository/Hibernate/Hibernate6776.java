	@Test
	public void testMappedBy() throws Exception {
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
		Store store;
		Supplier supplier;
		store = (Store) s.get( Store.class, fnac.getId() );
		assertNotNull( store );
		assertNotNull( store.getSuppliers() );
		assertEquals( 1, store.getSuppliers().size() );
		supplier = store.getSuppliers().iterator().next();
		assertEquals( emi.getName(), supplier.getName() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		supplier = (Supplier) s.get( Supplier.class, emi.getId() );
		assertNotNull( supplier );
		assertNotNull( supplier.getSuppStores() );
		assertEquals( 1, supplier.getSuppStores().size() );
		store = supplier.getSuppStores().iterator().next();
		assertEquals( fnac.getName(), store.getName() );
		tx.commit();
		s.close();
	}
