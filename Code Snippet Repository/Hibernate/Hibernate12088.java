	@SuppressWarnings("")
	public static <S extends Annotation, P extends Annotation> List<S> collectAnnotations(
			Class<S> singularAnnotationClass,
			Class<P> pluralAnnotationClass,
			FrameworkMethod frameworkMethod,
			TestClass testClass) {
		final List<S> collection = new LinkedList<S>();
		final S singularAnn = Helper.locateAnnotation( singularAnnotationClass, frameworkMethod, testClass );
		if ( singularAnn != null ) {
			collection.add( singularAnn );
		}
		final P pluralAnn = Helper.locateAnnotation( pluralAnnotationClass, frameworkMethod, testClass );
		if ( pluralAnn != null ) {
			try {
				collection.addAll( Arrays.asList( (S[]) pluralAnnotationClass.getDeclaredMethods()[0].invoke( pluralAnn ) ) );
			}
			catch (Exception e) {
				throw new RuntimeException( e );
			}
		}
		return collection;
	}
