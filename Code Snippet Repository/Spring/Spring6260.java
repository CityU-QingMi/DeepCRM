		private Connection getTargetConnection(Method operation) throws SQLException {
			if (this.target == null) {
				// No target Connection held -> fetch one.
				if (logger.isDebugEnabled()) {
					logger.debug("Connecting to database for operation '" + operation.getName() + "'");
				}

				// Fetch physical Connection from DataSource.
				this.target = (this.username != null) ?
						obtainTargetDataSource().getConnection(this.username, this.password) :
						obtainTargetDataSource().getConnection();

				// If we still lack default connection properties, check them now.
				checkDefaultConnectionProperties(this.target);

				// Apply kept transaction settings, if any.
				if (this.readOnly) {
					try {
						this.target.setReadOnly(true);
					}
					catch (Exception ex) {
						// "read-only not supported" -> ignore, it's just a hint anyway
						logger.debug("Could not set JDBC Connection read-only", ex);
					}
				}
				if (this.transactionIsolation != null &&
						!this.transactionIsolation.equals(defaultTransactionIsolation())) {
					this.target.setTransactionIsolation(this.transactionIsolation);
				}
				if (this.autoCommit != null && this.autoCommit != this.target.getAutoCommit()) {
					this.target.setAutoCommit(this.autoCommit);
				}
			}

			else {
				// Target Connection already held -> return it.
				if (logger.isDebugEnabled()) {
					logger.debug("Using existing database connection for operation '" + operation.getName() + "'");
				}
			}

			return this.target;
		}
