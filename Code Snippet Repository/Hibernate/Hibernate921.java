		private String columns(Value value) {
			final StringBuilder builder = new StringBuilder();
			final Iterator<Selectable> selectableItr = value.getColumnIterator();
			while ( selectableItr.hasNext() ) {
				builder.append( selectableItr.next().getText() );
				if ( selectableItr.hasNext() ) {
					builder.append( ", " );
				}
			}
			return builder.toString();
		}
