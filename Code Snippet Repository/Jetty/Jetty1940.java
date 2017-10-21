    public List<File> getJettyXmlFiles()
    {
        if ( this.jettyXml == null )
        {
            return null;
        }
        
        List<File> jettyXmlFiles = new ArrayList<File>();
        
        if ( this.jettyXml.indexOf(',') == -1 )
        {
            jettyXmlFiles.add( new File( this.jettyXml ) );
        }
        else
        {
            String[] files = StringUtil.csvSplit(this.jettyXml);
            
            for ( String file : files )
            {
                jettyXmlFiles.add( new File(file) );
            }
        }
        
        return jettyXmlFiles;
    }
