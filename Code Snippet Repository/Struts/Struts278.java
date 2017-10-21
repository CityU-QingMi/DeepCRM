	public static Method getPrefixedMethod(String[] prefixes, String methodName, Object action) {
		assert(prefixes != null);
		String capitalizedMethodName = capitalizeMethodName(methodName);
        for (String prefixe : prefixes) {
            String prefixedMethodName = prefixe + capitalizedMethodName;
            try {
                return action.getClass().getMethod(prefixedMethodName, EMPTY_CLASS_ARRAY);
            }
            catch (NoSuchMethodException e) {
                // hmm -- OK, try next prefix
                LOG.debug("Cannot find method [{}] in action [{}]", prefixedMethodName, action);
            }
        }
		return null;
	}
