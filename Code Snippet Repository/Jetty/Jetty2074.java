    public String toString()
    {
        StringBuilder result = new StringBuilder();
        
        result.append(_min);
        result.append("<=");
        result.append(getNameString());
        result.append("<=");
        result.append(_max);
        
        return result.toString();
    }
