		@SuppressWarnings("")
		public void resetSessionState() {
			if (this.previousFlushMode != null) {
				this.session.setFlushMode(this.previousFlushMode);
			}
			if (this.preparedCon != null && this.session.isConnected()) {
				Connection conToReset = HibernateConnectionHandle.doGetConnection(this.session);
				if (conToReset != this.preparedCon) {
					LogFactory.getLog(HibernateJpaDialect.class).warn(
							"JDBC Connection to reset not identical to originally prepared Connection - please " +
							"make sure to use connection release mode ON_CLOSE (the default) and to run against " +
							"Hibernate 4.2+ (or switch HibernateJpaDialect's prepareConnection flag to false");
				}
				DataSourceUtils.resetConnectionAfterTransaction(conToReset, this.previousIsolationLevel);
			}
		}
