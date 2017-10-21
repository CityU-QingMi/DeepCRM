    public void testExpOverridesCanStackExpUp() throws Exception {
        Map expr1 = new LinkedHashMap();
        expr1.put("expr1", "'expr1value'");

        OgnlValueStack vs = createValueStack();
        vs.setExprOverrides(expr1);

        assertEquals(vs.findValue("expr1"), "expr1value");

        Map expr2 = new LinkedHashMap();
        expr2.put("expr2", "'expr2value'");
        expr2.put("expr3", "'expr3value'");
        vs.setExprOverrides(expr2);

        assertEquals(vs.findValue("expr2"), "expr2value");
        assertEquals(vs.findValue("expr3"), "expr3value");
    }
