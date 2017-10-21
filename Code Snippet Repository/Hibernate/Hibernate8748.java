	@Test
	public void testQueryUsingLockOptions() {
		// todo : need an association here to make sure the alias-specific lock modes are applied correctly
		doInHibernate( this::sessionFactory, session -> {
			session.createQuery( "from A a" )
					.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE ) )
					.uniqueResult();
			session.createQuery( "from A a" )
					.setLockOptions( new LockOptions().setAliasSpecificLockMode( "a", LockMode.PESSIMISTIC_WRITE ) )
					.uniqueResult();
		} );
	}
