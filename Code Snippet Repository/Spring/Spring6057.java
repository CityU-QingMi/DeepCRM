		public String tryToInvokeWithNull3(Integer value, String... strings) {
			StringBuilder sb = new StringBuilder();
			for (String string : strings) {
				if (string == null) {
					sb.append("null");
				}
				else {
					sb.append(string);
				}
			}
			return sb.toString();
		}
