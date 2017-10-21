	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder("Parameter conditions ");
		int i = 0;
		for (String[] conditions : this.paramConditions) {
			if (i > 0) {
				sb.append(" OR ");
			}
			sb.append("\"");
			sb.append(StringUtils.arrayToDelimitedString(conditions, ", "));
			sb.append("\"");
			i++;
		}
		sb.append(" not met for actual request parameters: ");
		sb.append(requestParameterMapToString(this.actualParams));
		return sb.toString();
	}
