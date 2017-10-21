	private Class<?> doGetType(String name) throws NamingException {
		if (isSingleton(name)) {
			return doGetSingleton(name, null).getClass();
		}
		else {
			synchronized (this.resourceTypes) {
				if (this.resourceTypes.containsKey(name)) {
					return this.resourceTypes.get(name);
				}
				else {
					Class<?> type = lookup(name, null).getClass();
					this.resourceTypes.put(name, type);
					return type;
				}
			}
		}
	}
