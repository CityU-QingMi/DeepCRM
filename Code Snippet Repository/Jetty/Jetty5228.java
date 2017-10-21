    @Override
    public String[] list()
    {
        if(_resources==null)
            throw new IllegalStateException("*resources* not set.");
        
        HashSet<String> set = new HashSet<String>();
        for(Resource r : _resources)
        {
            for(String s : r.list())
                set.add(s);
        }
        String[] result=set.toArray(new String[set.size()]);
        Arrays.sort(result);
        return result;
    }
