    private boolean match (String filename)
    {
        if (StringUtil.isBlank(filename))
            return false;
        String[] parts = filename.split("_");
        
        //Need at least 4 parts for a valid filename
        if (parts.length < 4)
            return false;
        
        return true;
    }
