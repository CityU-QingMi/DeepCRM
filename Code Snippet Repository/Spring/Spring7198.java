	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		SimpAttributes simpAttributes = SimpAttributesContextHolder.currentAttributes();
		Object scopedObject = simpAttributes.getAttribute(name);
		if (scopedObject != null) {
			return scopedObject;
		}
		synchronized (simpAttributes.getSessionMutex()) {
			scopedObject = simpAttributes.getAttribute(name);
			if (scopedObject == null) {
				scopedObject = objectFactory.getObject();
				simpAttributes.setAttribute(name, scopedObject);
			}
			return scopedObject;
		}
	}
