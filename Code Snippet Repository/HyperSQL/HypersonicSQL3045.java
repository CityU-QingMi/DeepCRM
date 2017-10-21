    public ConnectionTypesSample() {

        // An example
        addElement("Example");
        addElement("org.example:ExampleDriver");
        addElement("jdbc:example");

        // Something useful
        addElement("Sybase");
        addElement("com.sybase.jdbc.SybDriver");
        addElement("jdbc:sybase:Tds:"
                   + "\u00ABhost?\u00BB:2638/\u00ABdatabase?\u00BB");
    }
