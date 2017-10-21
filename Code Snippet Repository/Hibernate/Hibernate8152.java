		private void cleanup() {
			Session s = openSession();
			Transaction txn = s.beginTransaction();

			s.createQuery( "delete Animal where description like 'grand%'" ).executeUpdate();
			s.createQuery( "delete Animal where not description like 'root%'" ).executeUpdate();
			s.createQuery( "delete Animal" ).executeUpdate();

			txn.commit();
			s.close();
		}
