		public SyntaxChecker checkScroll() {
			Session s = openSession();
			s.beginTransaction();
			Query query = s.createQuery( hql );
			preparer.prepare( query );
			query.scroll();
			s.getTransaction().commit();
			s.close();
			return this;
		}
