	@Test
	public void testSaveOrphanDeleteChildWithParent() {
		Session session = openSession();
		Transaction txn = session.beginTransaction();
		Parent parent = new Parent();
		DeleteOrphanChild child = new DeleteOrphanChild();
		child.setParent( parent );
		parent.setDeleteOrphanChildren( Collections.singleton( child ) );
		session.save( child );
		txn.commit();
		session.close();

		session = openSession();
		txn = session.beginTransaction();
		parent = ( Parent ) session.get( Parent.class, parent.getId() );
		assertEquals( 0, parent.getChildren().size() );
		assertEquals( 1, parent.getDeleteOrphanChildren().size() );
		session.delete( parent );
		txn.commit();
		session.close();
	}
