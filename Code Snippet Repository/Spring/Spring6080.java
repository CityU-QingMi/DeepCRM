	@Test
	public void NPE_SPR5673() throws Exception {
		ParserContext hashes = TemplateExpressionParsingTests.HASH_DELIMITED_PARSER_CONTEXT;
		ParserContext dollars = TemplateExpressionParsingTests.DEFAULT_TEMPLATE_PARSER_CONTEXT;

		checkTemplateParsing("abc${'def'} ghi", "abcdef ghi");

		checkTemplateParsingError("abc${ {}( 'abc'", "Missing closing ')' for '(' at position 8");
		checkTemplateParsingError("abc${ {}[ 'abc'", "Missing closing ']' for '[' at position 8");
		checkTemplateParsingError("abc${ {}{ 'abc'", "Missing closing '}' for '{' at position 8");
		checkTemplateParsingError("abc${ ( 'abc' }", "Found closing '}' at position 14 but most recent opening is '(' at position 6");
		checkTemplateParsingError("abc${ '... }", "Found non terminating string literal starting at position 6");
		checkTemplateParsingError("abc${ \"... }", "Found non terminating string literal starting at position 6");
		checkTemplateParsingError("abc${ ) }", "Found closing ')' at position 6 without an opening '('");
		checkTemplateParsingError("abc${ ] }", "Found closing ']' at position 6 without an opening '['");
		checkTemplateParsingError("abc${ } }", "No expression defined within delimiter '${}' at character 3");
		checkTemplateParsingError("abc$[ } ]", DOLLARSQUARE_TEMPLATE_PARSER_CONTEXT, "Found closing '}' at position 6 without an opening '{'");

		checkTemplateParsing("abc ${\"def''g}hi\"} jkl", "abc def'g}hi jkl");
		checkTemplateParsing("abc ${'def''g}hi'} jkl", "abc def'g}hi jkl");
		checkTemplateParsing("}", "}");
		checkTemplateParsing("${'hello'} world", "hello world");
		checkTemplateParsing("Hello ${'}'}]", "Hello }]");
		checkTemplateParsing("Hello ${'}'}", "Hello }");
		checkTemplateParsingError("Hello ${ ( ", "No ending suffix '}' for expression starting at character 6: ${ ( ");
		checkTemplateParsingError("Hello ${ ( }", "Found closing '}' at position 11 but most recent opening is '(' at position 9");
		checkTemplateParsing("#{'Unable to render embedded object: File ({#this == 2}'}", hashes, "Unable to render embedded object: File ({#this == 2}");
		checkTemplateParsing("This is the last odd number in the list: ${listOfNumbersUpToTen.$[#this%2==1]}", dollars, "This is the last odd number in the list: 9");
		checkTemplateParsing("Hello ${'here is a curly bracket }'}", dollars, "Hello here is a curly bracket }");
		checkTemplateParsing("He${'${'}llo ${'here is a curly bracket }'}}", dollars, "He${llo here is a curly bracket }}");
		checkTemplateParsing("Hello ${'()()()}{}{}{][]{}{][}[][][}{()()'} World", dollars, "Hello ()()()}{}{}{][]{}{][}[][][}{()() World");
		checkTemplateParsing("Hello ${'inner literal that''s got {[(])]}an escaped quote in it'} World", "Hello inner literal that's got {[(])]}an escaped quote in it World");
		checkTemplateParsingError("Hello ${", "No ending suffix '}' for expression starting at character 6: ${");
	}
