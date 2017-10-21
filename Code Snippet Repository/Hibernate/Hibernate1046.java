	private FieldDescription findField(String owner, String name, String desc) {
		TypePool.Resolution resolution = classPool.describe( owner.replace( '/', '.' ) );
		if ( !resolution.isResolved() ) {
			final String msg = String.format(
					"Unable to perform extended enhancement - Unable to locate [%s]",
					owner.replace( '/', '.' )
			);
			throw new EnhancementException( msg );
		}
		FieldList<?> fields = resolution.resolve().getDeclaredFields().filter( named( name ).and( hasDescriptor( desc ) ) );
		if ( fields.size() != 1 ) {
			final String msg = String.format(
					"Unable to perform extended enhancement - No unique field [%s] defined by [%s]",
					name,
					owner.replace( '/', '.' )
			);
			throw new EnhancementException( msg );
		}
		return fields.getOnly();
	}
