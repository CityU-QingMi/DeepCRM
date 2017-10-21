	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testReplicate() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Container baz = new Container();
		Contained f = new Contained();
		List list = new ArrayList();
		list.add(baz);
		f.setBag(list);
		List list2 = new ArrayList();
		list2.add(f);
		baz.setBag(list2);
		s.save(f);
		s.save(baz);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.replicate(baz, ReplicationMode.OVERWRITE);
		// HHH-2378
		SessionImpl x = (SessionImpl)s;
		EntityEntry entry = x.getPersistenceContext().getEntry( baz );
		assertNull(entry.getVersion());
		// ~~~~~~~
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.replicate(baz, ReplicationMode.IGNORE);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete(baz);
		s.delete(f);
		s.getTransaction().commit();
		s.close();
	}
