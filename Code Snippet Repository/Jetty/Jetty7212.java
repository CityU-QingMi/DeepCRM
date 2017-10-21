    private static XmlParser initParser()
    {
        XmlParser parser = new XmlParser();
        URL config60 = Loader.getResource("org/eclipse/jetty/xml/configure_6_0.dtd");
        URL config76 = Loader.getResource("org/eclipse/jetty/xml/configure_7_6.dtd");
        URL config90 = Loader.getResource("org/eclipse/jetty/xml/configure_9_0.dtd");
        URL config93 = Loader.getResource("org/eclipse/jetty/xml/configure_9_3.dtd");
        parser.redirectEntity("configure.dtd",config90);
        parser.redirectEntity("configure_1_0.dtd",config60);
        parser.redirectEntity("configure_1_1.dtd",config60);
        parser.redirectEntity("configure_1_2.dtd",config60);
        parser.redirectEntity("configure_1_3.dtd",config60);
        parser.redirectEntity("configure_6_0.dtd",config60);
        parser.redirectEntity("configure_7_6.dtd",config76);
        parser.redirectEntity("configure_9_0.dtd",config90);
        parser.redirectEntity("configure_9_3.dtd",config93);

        parser.redirectEntity("http://jetty.mortbay.org/configure.dtd",config93);
        parser.redirectEntity("http://jetty.eclipse.org/configure.dtd",config93);
        parser.redirectEntity("http://www.eclipse.org/jetty/configure.dtd",config93);

        parser.redirectEntity("-//Mort Bay Consulting//DTD Configure//EN",config93);
        parser.redirectEntity("-//Jetty//Configure//EN",config93);

        return parser;
    }
