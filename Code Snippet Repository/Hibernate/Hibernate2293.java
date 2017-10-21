		@Override
		public void cascade(
				EventSource session,
				Object child,
				String entityName,
				Object anything,
				boolean isCascadeDeleteEnabled) {
			LOG.tracev( "Cascading to lock: {0}", entityName );
			LockMode lockMode = LockMode.NONE;
			LockOptions lr = new LockOptions();
			if ( anything instanceof LockOptions ) {
				LockOptions lockOptions = (LockOptions) anything;
				lr.setTimeOut( lockOptions.getTimeOut() );
				lr.setScope( lockOptions.getScope() );
				lr.setFollowOnLocking( lockOptions.getFollowOnLocking() );
				if ( lockOptions.getScope() ) {
					lockMode = lockOptions.getLockMode();
				}
			}
			lr.setLockMode( lockMode );
			session.buildLockRequest( lr ).lock( entityName, child );
		}
