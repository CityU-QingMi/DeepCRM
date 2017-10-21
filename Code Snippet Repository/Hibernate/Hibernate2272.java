			@Override
			public boolean equals(Object o) {
				if ( this == o ) {
					return true;
				}
				if ( !( o instanceof BatchIdentifier ) ) {
					return false;
				}
				BatchIdentifier that = (BatchIdentifier) o;
				return Objects.equals( entityName, that.entityName );
			}
