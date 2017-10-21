	static boolean isInterfaceWithAnnotatedMethods(Class<?> iface) {
		Boolean found = annotatedInterfaceCache.get(iface);
		if (found != null) {
			return found;
		}
		found = Boolean.FALSE;
		for (Method ifcMethod : iface.getMethods()) {
			try {
				if (ifcMethod.getAnnotations().length > 0) {
					found = Boolean.TRUE;
					break;
				}
			}
			catch (Throwable ex) {
				handleIntrospectionFailure(ifcMethod, ex);
			}
		}
		annotatedInterfaceCache.put(iface, found);
		return found;
	}
