	@Test
	public void testMerge() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Course uc =  new UniversityCourse("mat2000", "Monash", "second year maths", 0);
		Course c =  new Course("eng5000", "BHS", "grade 5 english");
		s.persist(uc);
		s.persist(c);
		t.commit();
		s.close();
		
		c.setDescription("Grade 5 English");
		uc.setDescription("Second year mathematics");
		
		s = openSession();
		t = s.beginTransaction();
		s.merge(c);
		s.merge(uc);
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		s.delete(c);
		s.delete(uc);
		t.commit();
		s.close();
	}
