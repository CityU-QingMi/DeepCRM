    public void setResourceBases(String[] resourceBases)
    {
        List<String> resources = new ArrayList<String>();
        for (String rl:resourceBases)
        {
            String[] rs = StringUtil.csvSplit(rl);
            for (String r:rs)
                resources.add(r);
        }
        
        setBaseResource(new ResourceCollection(resources.toArray(new String[resources.size()])));
    }
