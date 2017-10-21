	@Test
	@Priority(10)
	@TestForIssue(jiraKey = "")
	public void testUpdatingDetachedEntityWithRelation() {
		Session session = getSession();

		// Revision 1
		session.getTransaction().begin();
		ListRefCollEntity parent = new ListRefCollEntity( 1, "initial data" );
		StrTestEntity child = new StrTestEntity( "data" );
		session.save( child );
		parent.setCollection( Arrays.asList( child ) );
		session.save( parent );
		session.getTransaction().commit();

		session.close();
		session = getSession();

		// Revision 2 - updating detached entity
		session.getTransaction().begin();
		parent.setData( "modified data" );
		session.update( parent );
		session.getTransaction().commit();

		session.close();

		parentId = parent.getId();
		childId = child.getId();
	}
