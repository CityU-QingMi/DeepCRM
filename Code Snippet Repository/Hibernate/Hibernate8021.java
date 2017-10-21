		private void cleanup() {
			Session s = openSession();
			Transaction txn = s.beginTransaction();

			// workaround awesome HSQLDB "feature"
			s.createQuery( "delete from Animal where mother is not null or father is not null" ).executeUpdate();
			s.createQuery( "delete from Animal" ).executeUpdate();
			s.createQuery( "delete from Zoo" ).executeUpdate();
			s.createQuery( "delete from Joiner" ).executeUpdate();
			s.createQuery( "delete from Vehicle" ).executeUpdate();
			s.createQuery( "delete from BooleanLiteralEntity" ).executeUpdate();

			txn.commit();
			s.close();
		}
