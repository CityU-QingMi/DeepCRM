	private void assertForeignKey(String foreignKeyName, String... columns) {
		Set<String> columnSet = new LinkedHashSet<>( Arrays.asList( columns ) );
		for ( Namespace namespace : metadata().getDatabase().getNamespaces() ) {
			for ( org.hibernate.mapping.Table table : namespace.getTables() ) {
				Iterator<org.hibernate.mapping.ForeignKey> fkItr = table.getForeignKeyIterator();
				while ( fkItr.hasNext() ) {
					org.hibernate.mapping.ForeignKey fk = fkItr.next();

					if ( foreignKeyName.equals( fk.getName() ) ) {
						assertEquals( "ForeignKey column count not like expected", columnSet.size(), fk.getColumnSpan() );
						List<String> columnNames = fk.getColumns().stream().map(Column::getName).collect(Collectors.toList());
						assertTrue(
								"ForeignKey columns [" + columnNames + "] do not match expected columns [" + columnSet + "]",
								columnSet.containsAll( columnNames )
						);
						return;
					}
				}
			}
		}
		fail( "ForeignKey '" + foreignKeyName + "' could not be found!" );
	}
