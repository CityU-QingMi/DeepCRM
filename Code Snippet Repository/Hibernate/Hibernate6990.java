	@Test
	public void testParameterValues() {

		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Entity1 entity1 = new Entity1();
		entity1.id = new Date( 0 );

		Entity2 entity2 = new Entity2();
		entity2.id = new Date( 0 );

		s.persist( entity1 );
		s.persist( entity2 );
		s.flush();
		s.clear();

		entity1 = (Entity1) s.byId( Entity1.class ).load( entity1.id );
		entity2 = (Entity2) s.byId( Entity2.class ).load( entity2.id );

		Assert.assertEquals( "ENTITY1.PROP1", entity1.entity1_Prop1 );
		Assert.assertEquals( "ENTITY1.PROP2", entity1.entity1_Prop2 );
		Assert.assertEquals( "ENTITY1.PROP3.FOO", entity1.entity1_Prop3 );
		Assert.assertEquals( "ENTITY1.PROP4.BAR", entity1.entity1_Prop4 );
		Assert.assertEquals( "ENTITY1.PROP5", entity1.entity1_Prop5 );
		Assert.assertEquals( "ENTITY1.PROP6", entity1.entity1_Prop6 );

		Assert.assertEquals( "ENTITY2.PROP1", entity2.entity2_Prop1 );
		Assert.assertEquals( "ENTITY2.PROP2", entity2.entity2_Prop2 );
		Assert.assertEquals( "ENTITY2.PROP3", entity2.entity2_Prop3 );
		Assert.assertEquals( "ENTITY2.PROP4", entity2.entity2_Prop4 );
		Assert.assertEquals( "ENTITY2.PROP5.BLAH", entity2.entity2_Prop5 );
		Assert.assertEquals( "ENTITY2.PROP6.YEAH", entity2.entity2_Prop6 );

		tx.rollback();
		s.close();
	}
