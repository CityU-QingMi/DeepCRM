    public ExtensionConfig(String parameterizedName)
    {
        Iterator<String> extListIter = QuoteUtil.splitAt(parameterizedName,";");
        this.name = extListIter.next();
        this.parameters = new HashMap<>();

        // now for parameters
        while (extListIter.hasNext())
        {
            String extParam = extListIter.next();
            Iterator<String> extParamIter = QuoteUtil.splitAt(extParam,"=");
            String key = extParamIter.next().trim();
            String value = null;
            if (extParamIter.hasNext())
            {
                value = extParamIter.next();
            }
            parameters.put(key,value);
        }
    }
