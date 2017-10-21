	public static JdbcCoordinatorImpl deserialize(
			ObjectInputStream ois,
			JdbcSessionOwner owner) throws IOException, ClassNotFoundException {
		final boolean isUserSuppliedConnection = ois.readBoolean();
		LogicalConnectionImplementor logicalConnection;
		if ( isUserSuppliedConnection ) {
			logicalConnection = LogicalConnectionProvidedImpl.deserialize( ois );
		}
		else {
			logicalConnection = LogicalConnectionManagedImpl.deserialize(
					ois,
					owner.getJdbcConnectionAccess(),
					owner.getJdbcSessionContext()
			);
		}
		return new JdbcCoordinatorImpl( logicalConnection, isUserSuppliedConnection, owner );
	}
