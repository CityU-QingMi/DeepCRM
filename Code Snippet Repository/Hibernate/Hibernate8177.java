		public void cleanup() {
			Session session = openSession();
			Transaction txn = session.beginTransaction();
			Human father = (Human) session.createQuery( "from Human where description = 'father'" ).uniqueResult();
			father.getFriends().clear();
			father.getFamily().clear();
			session.flush();
			session.delete( session.createQuery( "from Human where description = 'friend2'" ).uniqueResult() );
			session.delete( session.createQuery( "from Human where description = 'friend'" ).uniqueResult() );
			session.delete( session.createQuery( "from Human where description = 'child1'" ).uniqueResult() );
			session.delete( session.createQuery( "from Human where description = 'child2'" ).uniqueResult() );
			session.delete( session.createQuery( "from Human where description = 'mother'" ).uniqueResult() );
			session.delete( father );
			session.createQuery( "delete Animal" ).executeUpdate();
			txn.commit();
			session.close();
		}
