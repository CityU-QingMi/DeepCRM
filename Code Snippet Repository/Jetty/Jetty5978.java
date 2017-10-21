    public void processVersion ()
    {
        String version = _root.getAttribute("version", "DTD");
        if ("DTD".equals(version))
        {
            _majorVersion = 2;
            _minorVersion = 3;

            if (_dtd != null && _dtd.indexOf("web-app_2_2") >= 0)
            {
                _majorVersion = 2;
                _minorVersion = 2;
            }
        }
        else
        {
           int dot = version.indexOf(".");
           if (dot > 0)
           {
               _majorVersion = Integer.parseInt(version.substring(0,dot));
               _minorVersion = Integer.parseInt(version.substring(dot+1));
           }
        }

        if (_majorVersion <= 2 && _minorVersion < 5)
            _metaDataComplete = MetaDataComplete.True; // does not apply before 2.5
        else
        {
            String s = (String)_root.getAttribute("metadata-complete");
            if (s == null)
                _metaDataComplete = MetaDataComplete.NotSet;
            else
                _metaDataComplete = Boolean.valueOf(s).booleanValue()?MetaDataComplete.True:MetaDataComplete.False;
        }

        if (LOG.isDebugEnabled())
            LOG.debug(_xml.toString()+": Calculated metadatacomplete = " + _metaDataComplete + " with version=" + version);
    }
