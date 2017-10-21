	public static AnnotationMirror getAnnotationMirror(Element element, String fqcn) {
		assert element != null;
		assert fqcn != null;

		AnnotationMirror mirror = null;
		for ( AnnotationMirror am : element.getAnnotationMirrors() ) {
			if ( isAnnotationMirrorOfType( am, fqcn ) ) {
				mirror = am;
				break;
			}
		}
		return mirror;
	}
