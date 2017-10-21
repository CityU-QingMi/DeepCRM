    public Stats parseStats( String xml )
        throws Exception
    {
        XPath xPath = XPathFactory.newInstance().newXPath();

        String responses4xx = xPath.evaluate( "//responses4xx", new InputSource( new StringReader( xml ) ) );

        String responses2xx = xPath.evaluate( "//responses2xx", new InputSource( new StringReader( xml ) ) );

        return new Stats(Integer.parseInt( responses2xx), Integer.parseInt( responses4xx ));
    }
