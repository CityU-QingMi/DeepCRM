	@Override
	public String render(RenderingContext renderingContext) {
		PathSource<?> source = getPathSource();
		if ( source != null ) {
			source.prepareAlias( renderingContext );
			return source.getPathIdentifier() + "." + getAttribute().getName();
		}
		else {
			return getAttribute().getName();
		}
	}
