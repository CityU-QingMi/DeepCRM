    public void addOverride (Resource override)
    throws Exception
    {
        OverrideDescriptor webOverrideRoot = new OverrideDescriptor(override);
        webOverrideRoot.setValidating(false);
        webOverrideRoot.parse();

        switch(webOverrideRoot.getMetaDataComplete())
        {
            case True:
                _metaDataComplete=true;
                break;
            case False:
                _metaDataComplete=false;
                break;
            case NotSet:
                break;
        }

        if (webOverrideRoot.isOrdered())
        {
            Ordering ordering = getOrdering();
            
            if (ordering == null)
               ordering = new AbsoluteOrdering(this);

            List<String> order = webOverrideRoot.getOrdering();
            for (String s:order)
            {
                if (s.equalsIgnoreCase("others"))
                    ((AbsoluteOrdering)ordering).addOthers();
                else
                    ((AbsoluteOrdering)ordering).add(s);
            }
            
            //set or reset the ordering to cause the webinf jar ordering to be recomputed
            setOrdering(ordering);
        }
        _webOverrideRoots.add(webOverrideRoot);
    }
