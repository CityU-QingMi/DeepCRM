		@Override
		public String buildWriteInterceptionBodyFragment(String fieldName) {
			if ( inheritanceMetadata.isInherited() && !inheritanceMetadata.isVisible() ) {
				return String.format(
						"  %2$s localVar = $1;%n" +
						"  if ( %3$s() != null ) { localVar = (%2$s) %3$s().writeObject(this, \"%1$s\", super.%4$s(), $1); }%n" +
						"  super.%5$s(localVar);",
						fieldName,
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME,
						inheritanceMetadata.getReaderName(),
						inheritanceMetadata.getWriterName() );
			}
			else {
				return String.format(
						"  %2$s localVar = $1;%n" +
						"  if ( %3$s() != null ) { localVar = (%2$s) %3$s().writeObject(this, \"%1$s\", this.%1$s, $1); }%n" +
						"  this.%1$s = localVar;",
						fieldName,
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME );
			}
		}
