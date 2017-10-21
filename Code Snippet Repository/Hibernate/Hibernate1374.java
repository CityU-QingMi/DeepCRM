	public void setCache(Cache cacheAnn) {
		if ( cacheAnn != null ) {
			cacheRegion = BinderHelper.isEmptyAnnotationValue( cacheAnn.region() ) ?
					null :
					cacheAnn.region();
			cacheConcurrentStrategy = getCacheConcurrencyStrategy( cacheAnn.usage() );
			if ( "all".equalsIgnoreCase( cacheAnn.include() ) ) {
				cacheLazyProperty = true;
			}
			else if ( "non-lazy".equalsIgnoreCase( cacheAnn.include() ) ) {
				cacheLazyProperty = false;
			}
			else {
				throw new AnnotationException( "Unknown lazy property annotations: " + cacheAnn.include() );
			}
		}
		else {
			cacheConcurrentStrategy = null;
			cacheRegion = null;
			cacheLazyProperty = true;
		}
	}
