	@Test
	@Priority(10)
	public void shouldInit() {
		Session session = getSession();
		session.getTransaction().begin();

		ManyToOneEntity manyToOne = getManyToOneEntity();
		ManyToManyEntity manyToMany = getManyToManyEntity();
		OneToOneEntity oneToOne = getOneToOneEntity();

		PlainEntity plainEntity = getPlainEntity( manyToOne, manyToMany, oneToOne );

		session.save( manyToMany );
		session.save( manyToOne );
		session.save( oneToOne );
		session.save( plainEntity );

		session.getTransaction().commit();
		session.getTransaction().begin();
		PlainEntity load = (PlainEntity) session.load( PlainEntity.class, 1L );

		Assert.assertEquals( plainEntity, load );
		session.getTransaction().commit();

	}
