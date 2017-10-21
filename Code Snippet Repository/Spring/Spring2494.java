	public static ObjectName getInstance(Object objectName) throws MalformedObjectNameException {
		if (objectName instanceof ObjectName) {
			return (ObjectName) objectName;
		}
		if (!(objectName instanceof String)) {
			throw new MalformedObjectNameException("Invalid ObjectName value type [" +
					objectName.getClass().getName() + "]: only ObjectName and String supported.");
		}
		return getInstance((String) objectName);
	}
