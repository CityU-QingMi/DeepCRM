		@Override
		public String buildReadInterceptionBodyFragment(String fieldName) {
			if ( inheritanceMetadata.isInherited() && !inheritanceMetadata.isVisible() ) {
				return String.format(
						"  if (%3$s() != null ) { super.%5$s( %3$s().read%2$s(this, \"%1$s\", super.%4$s())); }",
						fieldName,
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME,
						inheritanceMetadata.getReaderName(),
						inheritanceMetadata.getWriterName() );
			}
			else {
				return String.format(
						"  if (%3$s() != null ) { this.%1$s = %3$s().read%2$s(this, \"%1$s\", this.%1$s); }",
						fieldName,
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME );
			}
		}
