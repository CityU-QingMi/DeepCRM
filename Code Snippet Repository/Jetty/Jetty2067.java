    public String toString()
    {
        StringBuilder result = new StringBuilder();
        
        result.append(getNameString());
        result.append("<=");
        result.append(_max);
        
        return result.toString();
    }
