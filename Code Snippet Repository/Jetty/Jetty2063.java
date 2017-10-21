    public String toString()
    {
        StringBuilder result = new StringBuilder();
        
        result.append(_min);
        result.append("<=");
        result.append(getNameString());
        
        return result.toString();
    }
