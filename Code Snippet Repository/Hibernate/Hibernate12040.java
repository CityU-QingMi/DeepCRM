		@Override
		public void rollback(Xid xid) throws XAException {

			try {
				connection.rollback();
			}
			catch(SQLException e) {
				throw new XAException( e.toString() );
			}
			finally {
				try {
					pool.delist( connection );
				}
				catch (Exception ignore) {
				}
			}
		}
