    private boolean matchStackTraces(StackTraceElement[] lastStackTrace, StackTraceElement[] stackTrace)
    {
        boolean match = true;
        int count = Math.min(_stackDepth, Math.min(lastStackTrace.length, stackTrace.length));
        
        for (int idx=0; idx < count; idx++)
        {
            if (!stackTrace[idx].equals(lastStackTrace[idx]))
            {
                match = false;
                break;
            }
        }
        return match;
    }
