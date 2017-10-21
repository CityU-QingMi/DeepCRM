	public static void updateCurrentPropertyPath(Map<String, Object> context, Object name) {
		String currentPath=getCurrentPropertyPath(context);
		if (name!=null) {
			if (currentPath!=null) {
                StringBuilder sb = new StringBuilder(currentPath);
                sb.append(".");
                sb.append(name.toString());
				currentPath = sb.toString();
			}	else {
				currentPath = name.toString();
			}
			context.put(CURRENT_PROPERTY_PATH, currentPath);
		}
	}
