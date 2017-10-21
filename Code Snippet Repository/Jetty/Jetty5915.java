    public void setDefaults (Resource webDefaults)
    throws Exception
    {
        _webDefaultsRoot =  new DefaultsDescriptor(webDefaults);
        _webDefaultsRoot.setValidating(isValidateXml());
        _webDefaultsRoot.parse();
        if (_webDefaultsRoot.isOrdered())
        {
            Ordering ordering = getOrdering();
            if (ordering == null)
               ordering = new AbsoluteOrdering(this);

            List<String> order = _webDefaultsRoot.getOrdering();
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
