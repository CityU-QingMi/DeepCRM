	@Override
	public CacheInvocationParameter[] getAllParameters(Object... values) {
		if (this.allParameterDetails.size() != values.length) {
			throw new IllegalStateException("Values mismatch, operation has " +
					this.allParameterDetails.size() + " parameter(s) but got " + values.length + " value(s)");
		}
		List<CacheInvocationParameter> result = new ArrayList<>();
		for (int i = 0; i < this.allParameterDetails.size(); i++) {
			result.add(this.allParameterDetails.get(i).toCacheInvocationParameter(values[i]));
		}
		return result.toArray(new CacheInvocationParameter[result.size()]);
	}
