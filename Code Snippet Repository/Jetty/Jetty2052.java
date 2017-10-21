    public String toString()
    {
        int cnt = 0;
        StringBuilder result = new StringBuilder();
        
        result.append("AND(");
        for (EventTrigger trigger : _triggers)
        {
            result.append(cnt++ > 0 ? "," : "");
            result.append(trigger);
        }
        result.append(')');
        
        return result.toString();
    }
