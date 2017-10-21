	public void testEmbeddedParamTagExpressionGetsEvaluatedCorrectly() throws Exception {
		request.setRequestURI("/public/about");
		request.setQueryString("section=team&company=acme inc");

		tag.setAction("team");
		tag.setIncludeParams("all");

		tag.doStartTag();

		Foo foo = new Foo("test");
		stack.push(foo);

		// include nested param tag
		ParamTag paramTag = new ParamTag();
		paramTag.setPageContext(pageContext);
		paramTag.setName("title");
		paramTag.setValue("%{title}");
		paramTag.doStartTag();
		paramTag.doEndTag();

		tag.doEndTag();

		assertEquals("/team.action?section=team&amp;company=acme+inc&amp;title=test", writer.toString());
	}
