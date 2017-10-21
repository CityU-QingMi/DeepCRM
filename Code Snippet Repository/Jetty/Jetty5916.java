    public void setWebXml (Resource webXml)
    throws Exception
    {
        _webXmlRoot = new WebDescriptor(webXml);
        _webXmlRoot.setValidating(isValidateXml());
        _webXmlRoot.parse();
        _metaDataComplete=_webXmlRoot.getMetaDataComplete() == MetaDataComplete.True;

        if (_webXmlRoot.isOrdered())
        {
            Ordering ordering = getOrdering();
            if (ordering == null)
                ordering = new AbsoluteOrdering(this);

            List<String> order = _webXmlRoot.getOrdering();
            for (String s:order)
            {
                if (s.equalsIgnoreCase("others"))
                    ((AbsoluteOrdering)ordering).addOthers();
                else
                    ((AbsoluteOrdering)ordering).add(s);
            }
            
            //(re)set the ordering to cause webinf jar order to be recalculated
            setOrdering(ordering);
        }
    }
