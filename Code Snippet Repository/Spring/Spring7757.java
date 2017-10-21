		@Override
		public void flush() {
			try {
				getSessionHolder().getSession().flush();
			}
			catch (HibernateException ex) {
				throw convertHibernateAccessException(ex);
			}
			catch (PersistenceException ex) {
				if (ex.getCause() instanceof HibernateException) {
					throw convertHibernateAccessException((HibernateException) ex.getCause());
				}
				throw ex;
			}
		}
