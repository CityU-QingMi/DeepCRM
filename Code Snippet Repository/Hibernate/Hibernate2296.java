		@Override
		public void cascade(
				EventSource session,
				Object child,
				String entityName,
				Object anything,
				boolean isCascadeDeleteEnabled)
				throws HibernateException {
			LOG.tracev( "Cascading to save or update: {0}", entityName );
			session.saveOrUpdate( entityName, child );
		}
