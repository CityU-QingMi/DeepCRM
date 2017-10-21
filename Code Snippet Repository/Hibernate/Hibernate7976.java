		public SyntaxChecker checkList() {
			Session s = openSession();
			s.beginTransaction();
			Query query = s.createQuery( hql );
			preparer.prepare( query );
			query.list();
			s.getTransaction().commit();
			s.close();
			return this;
		}
