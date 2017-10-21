    public void parse ()
    throws Exception
    {

        
        if (_root == null)
        {
            try
            {
                XmlParser parser = ensureParser();
                _root = parser.parse(_xml.getInputStream());
                _dtd = parser.getDTD();
            }
            finally
            {
                _xml.close();
            }
        }
    }
