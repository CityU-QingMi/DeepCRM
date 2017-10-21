    public static ELNode.Nodes parse(String expression) {
        ELParser parser = new ELParser(expression);
        while (parser.hasNextChar()) {
            String text = parser.skipUntilEL();
            if (text.length() > 0) {
                parser.expr.add(new ELNode.Text(text));
            }
            ELNode.Nodes elexpr = parser.parseEL();
            if (!elexpr.isEmpty()) {
                parser.expr.add(new ELNode.Root(elexpr, parser.type));
            }
        }
        return parser.expr;
    }
