		protected Serializable getResult(
				SharedSessionContractImplementor session,
				ResultSet rs,
				Object entity) throws SQLException {
			if ( !rs.next() ) {
				throw new IdentifierGenerationException(
						"the inserted row could not be located by the unique key: " +
								uniqueKeyPropertyName
				);
			}
			return (Serializable) idType.nullSafeGet(
					rs,
					persister.getRootTableKeyColumnNames(),
					session,
					entity
			);
		}
