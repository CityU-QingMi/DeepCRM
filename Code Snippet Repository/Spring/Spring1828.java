	private static CacheParameterDetail initializeValueParameterDetail(
			Method method, List<CacheParameterDetail> allParameters) {

		CacheParameterDetail result = null;
		for (CacheParameterDetail parameter : allParameters) {
			if (parameter.isValue()) {
				if (result == null) {
					result = parameter;
				}
				else {
					throw new IllegalArgumentException("More than one @CacheValue found on " + method + "");
				}
			}
		}
		return result;
	}
