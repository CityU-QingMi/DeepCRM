	@Nullable
	protected String getDefaultResponseDestination() {
		Method specificMethod = getMostSpecificMethod();
		if (specificMethod == null) {
			return null;
		}
		SendTo ann = getSendTo(specificMethod);
		if (ann != null) {
			Object[] destinations = ann.value();
			if (destinations.length != 1) {
				throw new IllegalStateException("Invalid @" + SendTo.class.getSimpleName() + " annotation on '" +
						specificMethod + "' one destination must be set (got " + Arrays.toString(destinations) + ")");
			}
			return resolve((String) destinations[0]);
		}
		return null;
	}
