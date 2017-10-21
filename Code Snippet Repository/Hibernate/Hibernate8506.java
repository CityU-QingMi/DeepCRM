	@Test
	public void testSaveOrUpdateCopyAny() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Bar bar = new Bar();
		One one = new One();
		bar.setObject(one);
		s.save(bar);
		GlarchProxy g = bar.getComponent().getGlarch();
		bar.getComponent().setGlarch(null);
		s.delete(g);
		s.flush();
		assertTrue( s.contains(one) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Bar bar2 = (Bar) s.merge( bar );
		s.flush();
		s.delete(bar2);
		s.flush();
		s.getTransaction().commit();
		s.close();
	}
