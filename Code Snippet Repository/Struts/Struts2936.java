    public static void main(String args[]) {
	SmapGenerator g = new SmapGenerator();
	g.setOutputFileName("foo.java");
	SmapStratum s = new SmapStratum("JSP");
	s.addFile("foo.jsp");
	s.addFile("bar.jsp", "/foo/foo/bar.jsp");
	s.addLineData(1, "foo.jsp", 1, 1, 1);
	s.addLineData(2, "foo.jsp", 1, 6, 1);
	s.addLineData(3, "foo.jsp", 2, 10, 5);
	s.addLineData(20, "bar.jsp", 1, 30, 1);
	g.addStratum(s, true);
	System.out.print(g);

	System.out.println("---");

	SmapGenerator embedded = new SmapGenerator();
	embedded.setOutputFileName("blargh.tier2");
	s = new SmapStratum("Tier2");
	s.addFile("1.tier2");
	s.addLineData(1, "1.tier2", 1, 1, 1);
	embedded.addStratum(s, true);
	g.addSmap(embedded.toString(), "JSP");
	System.out.println(g);
    }
