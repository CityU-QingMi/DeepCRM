    public void setValidating(boolean validating)
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(validating);
            _parser = factory.newSAXParser();

            try
            {
                if (validating)
                    _parser.getXMLReader().setFeature("http://apache.org/xml/features/validation/schema", validating);
            }
            catch (Exception e)
            {
                if (validating)
                    LOG.warn("Schema validation may not be supported: ", e);
                else
                    LOG.ignore(e);
            }

            _parser.getXMLReader().setFeature("http://xml.org/sax/features/validation", validating);
            _parser.getXMLReader().setFeature("http://xml.org/sax/features/namespaces", true);
            _parser.getXMLReader().setFeature("http://xml.org/sax/features/namespace-prefixes", false);
            try
            {
                if (validating)
                    _parser.getXMLReader().setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", validating);
            }
            catch (Exception e)
            {
                LOG.warn(e.getMessage());
            }
        }
        catch (Exception e)
        {
            LOG.warn(Log.EXCEPTION, e);
            throw new Error(e.toString());
        }
    }
