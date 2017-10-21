		@Override
		public String buildReadInterceptionBodyFragment(String fieldName) {
			if ( inheritanceMetadata.isInherited() && !inheritanceMetadata.isVisible() ) {
				return String.format(
						" if( %3$s() != null ) { super.%5$s( (%2$s) %3$s().readObject(this, \"%1$s\", super.%4$s())); }%n",
						fieldName,
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME,
						inheritanceMetadata.getReaderName(),
						inheritanceMetadata.getWriterName() );
			}
			else {
				return String.format(
						"  if ( %3$s() != null ) { this.%1$s = (%2$s) %3$s().readObject(this, \"%1$s\", this.%1$s); }%n",
						fieldName,
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME );
			}
		}
