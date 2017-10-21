	public String getHtml(HttpServletRequest request) {
		String ldapUser = "";
		String ldapPass  = "";
		HttpSession session = request.getSession(true);
		if (session.getAttribute("ldapUser") == null) {
			session.setAttribute("ldapUser", "");
			session.setAttribute("ldapPass", "");
		} else {
			// preserve user data
			String ldapUserTemp = request.getParameter("ldapUser");
			String ldapPassTemp = request.getParameter("ldapPass");
			ldapUser = ldapUserTemp != null ? ldapUserTemp : "";
			ldapPass = ldapPassTemp != null ? ldapPassTemp : "";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("<p>");
		sb.append(bundle.getString("comments.ldapAuthenticatorUserName"));
		sb.append("</p>");
		sb.append("<p>");
		sb.append("<input name=\"ldapUser\" value=\"");
		sb.append(ldapUser + "\">");
		sb.append("</p>");
		sb.append("<p>");
		sb.append(bundle.getString("comments.ldapAuthenticatorPassword"));
		sb.append("</p>");
		sb.append("<p>");
		sb.append("<input type=\"password\" name=\"ldapPass\" value=\"");
		sb.append(ldapPass + "\">");
		sb.append("</p>");

		return sb.toString();
	}
