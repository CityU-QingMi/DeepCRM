	@Test
	public void testUnidirCollectionWithMultipleOwner() throws Exception {
		Session s = openSession();
		Transaction tx;
		tx = s.beginTransaction();
		Father father = new Father();
		Mother mother = new Mother();
		s.save( father );
		//s.save( mother );
		Son son = new Son();
		father.getOrderedSons().add( son );
		son.setFather( father );
		mother.getSons().add( son );
		son.setMother( mother );
		s.save( mother );
		s.save( father );
		tx.commit();

		s.clear();

		tx = s.beginTransaction();
		son = (Son) s.get( Son.class, son.getId() );
		s.delete( son );
		s.flush();
		father = (Father) s.get( Father.class, father.getId() );
		mother = (Mother) s.get( Mother.class, mother.getId() );
		s.delete( father );
		s.delete( mother );
		tx.commit();
		s.close();
	}
