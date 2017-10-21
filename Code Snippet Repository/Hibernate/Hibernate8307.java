	@Test
	public void doIt() {
		Session session = openSession();
		session.beginTransaction();
		// we need the 2 inserts so that the id is incremented on the second get-generated-keys-result set, since
		// on the first insert both the pk and the discriminator values are 1
		session.save( new InheritingEntity( "yabba" ) );
		session.save( new InheritingEntity( "dabba" ) );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		session.createQuery( "delete ParentEntity" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
