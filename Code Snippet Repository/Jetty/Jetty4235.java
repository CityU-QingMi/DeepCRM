    @ManagedAttribute("")
    public String getWhitelist()
    {
        StringBuilder result = new StringBuilder();
        for (Iterator<String> iterator = _whitelist.iterator(); iterator.hasNext();)
        {
            String address = iterator.next();
            result.append(address);
            if (iterator.hasNext())
                result.append(",");
        }
        return result.toString();
    }
