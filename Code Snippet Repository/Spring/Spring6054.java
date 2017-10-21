	@Test
	public void reservedWords_8228() throws Exception {
		// "DIV","EQ","GE","GT","LE","LT","MOD","NE","NOT"
		@SuppressWarnings("unused")
		class Reserver {
			public Reserver getReserver() {
				return this;
			}
			public String NE = "abc";
			public String ne = "def";

			public int DIV = 1;
			public int div = 3;

			public Map<String, String> m = new HashMap<>();

			Reserver() {
				m.put("NE", "xyz");
			}
		}

		StandardEvaluationContext ctx = new StandardEvaluationContext(new Reserver());
		SpelExpressionParser parser = new SpelExpressionParser();
		String ex = "getReserver().NE";
		SpelExpression exp = parser.parseRaw(ex);
		String value = (String) exp.getValue(ctx);
		assertEquals("abc", value);

		ex = "getReserver().ne";
		exp = parser.parseRaw(ex);
		value = (String) exp.getValue(ctx);
		assertEquals("def", value);

		ex = "getReserver().m[NE]";
		exp = parser.parseRaw(ex);
		value = (String) exp.getValue(ctx);
		assertEquals("xyz", value);

		ex = "getReserver().DIV";
		exp = parser.parseRaw(ex);
		assertEquals(1, exp.getValue(ctx));

		ex = "getReserver().div";
		exp = parser.parseRaw(ex);
		assertEquals(3, exp.getValue(ctx));

		exp = parser.parseRaw("NE");
		assertEquals("abc", exp.getValue(ctx));
	}
