		public SyntaxChecker checkIterate() {
			Session s = openSession();
			s.beginTransaction();
			Query query = s.createQuery( hql );
			preparer.prepare( query );
			query.iterate();
			s.getTransaction().commit();
			s.close();
			return this;
		}
