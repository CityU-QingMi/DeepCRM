    public String toString()
    {
        int cnt = 0;
        StringBuilder result = new StringBuilder();
        
        for (TriggerState<TYPE> value : _states.values())
        {
            result.append(cnt++>0?"#":"");
            result.append(value.toString());
        }
        
        return result.toString();
    }
