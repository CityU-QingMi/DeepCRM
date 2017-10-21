	private String getQualifedDc(String ldapDc, String ldapOu, String ldapUser) {
		String qualifedDc = "";
		for (String token : StringUtils.delimitedListToStringArray(ldapDc, ",")) {
			if (!qualifedDc.isEmpty()) {
				qualifedDc += ",";
			}
			qualifedDc += "dc=" + token;
		}
		
		return "uid=" + ldapUser + ", ou=" + ldapOu + "," + qualifedDc;
	}
