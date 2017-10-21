		@Override
		public String render(RenderingContext renderingContext) {
			PathSource<?> source = getPathSource();
			String name;
			if ( source != null ) {
				source.prepareAlias( renderingContext );
				name = source.getPathIdentifier();
			}
			else {
				name = getAttribute().getName();
			}
			return "key(" + name + ")";
		}
