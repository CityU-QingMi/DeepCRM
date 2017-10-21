	private void assertNoForeignKey(String foreignKeyName, String... columns) {
		Set<String> columnSet = new LinkedHashSet<>( Arrays.asList( columns ) );
		for ( Namespace namespace : metadata().getDatabase().getNamespaces() ) {
			for ( org.hibernate.mapping.Table table : namespace.getTables() ) {
				Iterator<org.hibernate.mapping.ForeignKey> fkItr = table.getForeignKeyIterator();
				while ( fkItr.hasNext() ) {
					org.hibernate.mapping.ForeignKey fk = fkItr.next();
					assertFalse(
							"ForeignKey [" + foreignKeyName + "] defined and shouldn't have been.",
							foreignKeyName.equals( fk.getName() )
					);
				}
			}
		}
	}
