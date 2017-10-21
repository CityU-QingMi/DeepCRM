	protected boolean isAllTestsIgnored() {
		if ( isAllTestsIgnored == null ) {
			if ( computeTestMethods().isEmpty() ) {
				isAllTestsIgnored = true;
			}
			else {
				isAllTestsIgnored = true;
				for ( FrameworkMethod method : computeTestMethods() ) {
					Ignore ignore = method.getAnnotation( Ignore.class );
					if ( ignore == null ) {
						isAllTestsIgnored = false;
						break;
					}
				}
			}
		}
		return isAllTestsIgnored;
	}
