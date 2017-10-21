	@Test
	public void testRefreshProxy() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Glarch g = new Glarch();
		Serializable gid = s.save(g);
		s.flush();
		s.clear();
		GlarchProxy gp = (GlarchProxy) s.load(Glarch.class, gid);
		gp.getName(); //force init
		s.refresh(gp);
		s.delete(gp);
		s.flush();
		s.getTransaction().commit();
		s.close();
	}
