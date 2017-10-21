	private Object readValue(Map.Entry<? ,?> entry) {
		Object val = entry.getValue();
		if (val instanceof String) {
			String strVal = (String) val;
			// If it starts with a reference prefix...
			if (strVal.startsWith(REF_PREFIX)) {
				// Expand the reference.
				String targetName = strVal.substring(1);
				if (targetName.startsWith(REF_PREFIX)) {
					// Escaped prefix -> use plain value.
					val = targetName;
				}
				else {
					val = new RuntimeBeanReference(targetName);
				}
			}
		}
		return val;
	}
