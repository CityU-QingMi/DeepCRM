	@Test
	public void testPropertyRef() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Holder h = new Holder();
		h.setName("foo");
		Holder h2 = new Holder();
		h2.setName("bar");
		h.setOtherHolder(h2);
		Serializable hid = s.save(h);
		Qux q = new Qux();
		q.setHolder(h2);
		Serializable qid = s.save(q);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		h = (Holder) s.load(Holder.class, hid);
		assertEquals( h.getName(), "foo");
		assertEquals( h.getOtherHolder().getName(), "bar");
		Object[] res = (Object[]) s.createQuery( "from Holder h join h.otherHolder oh where h.otherHolder.name = 'bar'" )
				.list()
				.get(0);
		assertTrue( res[0]==h );
		q = (Qux) s.get(Qux.class, qid);
		assertTrue( q.getHolder() == h.getOtherHolder() );
		s.delete(h);
		s.delete(q);
		s.getTransaction().commit();
		s.close();
	}
