	public void testAccessToStackInternalsGetsHandledCorrectly() throws Exception {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("aaa", new String[] {"1${#session[\"foo\"]='true'}"});
		params.put("aab", new String[]{"1${#session[\"bar\"]}"});
		params.put("aac", new String[]{"1${#_memberAccess[\"allowStaticMethodAccess\"]='true'}"});
		params.put("aad", new String[]{"1${#_memberAccess[\"allowStaticMethodAccess\"]}"});

		request.setParameterMap(params);
		request.setRequestURI("/public/about");
		request.setQueryString("aae${%23session[\"bar\"]}=1%24%7B%23session%5B%22bar%22%5D%7D");
		session.put("bar", "rab");

		tag.setAction("team");
		tag.setIncludeParams("all");

		tag.doStartTag();
		tag.doEndTag();

		Object allowMethodAccess = stack.findValue("\u0023_memberAccess['allowStaticMethodAccess']");
		assertNull(allowMethodAccess);

		assertNull(session.get("foo"));

		assertEquals("/team.action?aaa=1%24%7B%23session%5B%22foo%22%5D%3D%27true%27%7D" +
                        "&amp;" +
                        "aab=1%24%7B%23session%5B%22bar%22%5D%7D" +
                        "&amp;" +
                        "aac=1%24%7B%23_memberAccess%5B%22allowStaticMethodAccess%22%5D%3D%27true%27%7D" +
                        "&amp;" +
                        "aad=1%24%7B%23_memberAccess%5B%22allowStaticMethodAccess%22%5D%7D" +
                        "&amp;" +
                        "aae%24%7B%23session%5B%22bar%22%5D%7D=1%24%7B%23session%5B%22bar%22%5D%7D"
				, writer.toString()
		);
	}
