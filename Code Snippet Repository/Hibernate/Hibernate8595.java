	@Test
	public void testOneToOnePropertyRef() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		s.createQuery("from Commento c where c.marelo.mlmag = 0").list();
		s.createQuery("from Commento c where c.marelo.commento.mcompr is null").list();
		s.createQuery("from Commento c where c.marelo.mlink = 0").list();
		s.createQuery("from Commento c where c.marelo.commento = c").list();
		s.createQuery("from Commento c where c.marelo.id.mlmag = 0").list();
		s.createQuery("from Commento c where c.marelo.commento.id = c.id").list();
		s.createQuery("from Commento c where c.marelo.commento.mclink = c.mclink").list();
		s.createQuery("from Marelo m where m.commento.id > 0").list();
		s.createQuery("from Marelo m where m.commento.marelo.commento.marelo.mlmag is not null").list();
		s.getTransaction().commit();
		s.close();
	}
