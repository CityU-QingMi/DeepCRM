		public Object execute(boolean isSingleResult) throws Exception{
			Session s = openSession();
			Transaction t = s.beginTransaction();
			Object result = null;
			try {
				result = getResults( s, isSingleResult );
				t.commit();
			}
			catch ( Exception ex ) {
				t.rollback();
				throw ex;
			}
			finally {
				s.close();
			}
			return result;
		}
