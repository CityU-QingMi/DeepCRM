	private Map getEntityMap(Class entityClass) {
		if (entityClass != null) {
			Map tryMap = (Map) memory.get(entityClass);
			if (tryMap == null) {
				synchronized (memory) {
					tryMap = new HashMap();
					memory.put(entityClass, tryMap);
				}
			}
			return tryMap;
		} else {
			return null;
		}
	}
