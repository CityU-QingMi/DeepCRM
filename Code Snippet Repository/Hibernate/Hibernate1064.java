		@Override
		public String buildWriteInterceptionBodyFragment(String fieldName) {
			if ( inheritanceMetadata.isInherited() && !inheritanceMetadata.isVisible() ) {
				return String.format(
						"  %2$s localVar = $1;%n" +
						"  if ( %4$s() != null ) { localVar = %4$s().write%3$s(this, \"%1$s\", super.%5$s(), $1); }%n" +
						"  super.%6$s(localVar);",
						fieldName,
						type.toLowerCase( Locale.ROOT ),
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME,
						inheritanceMetadata.getReaderName(),
						inheritanceMetadata.getWriterName() );
			}
			else {
				return String.format(
						"  %2$s localVar = $1;%n" +
						"  if ( %4$s() != null ) { localVar = %4$s().write%3$s(this, \"%1$s\", this.%1$s, $1); }%n" +
						"  this.%1$s = localVar;",
						fieldName,
						type.toLowerCase( Locale.ROOT ),
						type,
						EnhancerConstants.INTERCEPTOR_GETTER_NAME
				);
			}
		}
