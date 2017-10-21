		public static String toDelimitedString(String... elements) {
			StringBuilder rtn = new StringBuilder();
			for (String element : elements) {
				if (StringUtils.hasLength(element)) {
					rtn.append(rtn.length() == 0 ? "" : CODE_SEPARATOR);
					rtn.append(element);
				}
			}
			return rtn.toString();
		}
