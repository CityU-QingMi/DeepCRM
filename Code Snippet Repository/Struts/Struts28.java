	public Object convertFromString(Map context, String[] values, Class toClass) {

		if (values.length > 0 && values[0] != null && values[0].trim().length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			try {
				return sdf.parse(values[0]);
			} catch (ParseException e) {
				LOG.error("error converting value [" + values[0] + "] to Date ", e);
			}
		}
		return null;
	}
