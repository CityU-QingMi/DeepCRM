	public long getDateHeader(String name) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
		dateFormat.setTimeZone(GMT);
		try {
			return dateFormat.parse(getHeader(name)).getTime();
		}
		catch (ParseException ex) {
			throw new IllegalArgumentException(
					"Value for header '" + name + "' is not a valid Date: " + getHeader(name));
		}
	}
