	public void setNaturalIdCache(XClass clazzToProcess, NaturalIdCache naturalIdCacheAnn) {
		if ( naturalIdCacheAnn != null ) {
			if ( BinderHelper.isEmptyAnnotationValue( naturalIdCacheAnn.region() ) ) {
				if (cacheRegion != null) {
					naturalIdCacheRegion = cacheRegion + NATURAL_ID_CACHE_SUFFIX;
				}
				else {
					naturalIdCacheRegion = clazzToProcess.getName() + NATURAL_ID_CACHE_SUFFIX;
				}
			}
			else {
				naturalIdCacheRegion = naturalIdCacheAnn.region();
			}
		}
		else {
			naturalIdCacheRegion = null;
		}
	}
