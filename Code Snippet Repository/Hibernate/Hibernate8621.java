	@Test
	public void testCollectionPointer() throws Exception {
		Session sess = openSession();
		sess.beginTransaction();
		Lower ls = new Lower();
		List list = new ArrayList();
		ls.setBag(list);
		Top s = new Top();
		Serializable id = sess.save(ls);
		sess.save(s);
		sess.flush();
		list.add(s);
		sess.getTransaction().commit();
		sess.close();

		sess = openSession();
		sess.beginTransaction();
		ls = (Lower) sess.load(Lower.class, id);
		assertTrue( ls.getBag().size()==1 );
		doDelete( sess, "from java.lang.Object" );
		sess.getTransaction().commit();
		sess.close();
	}
