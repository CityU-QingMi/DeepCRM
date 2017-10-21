	@Test
	public void hhh11655Test() throws Exception {
		Session session = openSession();
		session.beginTransaction();

		SimpleEntity simpleEntityNonProxy = new SimpleEntity();
		Assert.assertFalse( session.contains( simpleEntityNonProxy ) );

		SimpleEntity simpleEntity = MyEntityInstantiator.createInstance( SimpleEntity.class );
		Assert.assertFalse( session.contains( simpleEntity ) );

		session.persist( simpleEntity );
		Assert.assertTrue( session.contains( simpleEntity ) );

		session.getTransaction().rollback();
		session.close();
	}
