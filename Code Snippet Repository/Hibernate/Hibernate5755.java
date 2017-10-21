	@Override
	public Class[] getAnnotatedClasses() {
		List<Class> classes = new ArrayList<>();
		Collections.addAll( classes, super.getAnnotatedClasses() );
		classes.add( MapEntity.class );
		classes.add( MapEntityLocal.class );
		classes.add( Article.class );
		classes.add( Translation.class );

		return classes.toArray( new Class[ classes.size() ] );
	}
