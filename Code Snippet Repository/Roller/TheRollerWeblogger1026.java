    public void testLinkbackExtractor() throws Exception {
        String[][] testrefs = new String[][]
        {
            // Second URL contains a link to the first
            {
                "http://cassandra.apache.org/",
                        "http://rollerweblogger.org/roller/entry/composite_keys_in_cassandra"
            },
            {
                "http://roller.apache.org/downloads/downloads.html",
                        "http://rollerweblogger.org/project/date/20140627"
            }
        };

        LinkbackExtractor le = new LinkbackExtractor(testrefs[0][0],testrefs[0][1]);
        assertEquals("The Apache Cassandra Project", le.getTitle());
        
        le = new LinkbackExtractor(testrefs[1][0],testrefs[1][1]);
        assertEquals("Apache Roller", le.getTitle());

        // todo: le.getPermalink() and le.getExcerpt() working
    }
