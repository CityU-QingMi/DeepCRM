    private long calculateLength()
    {
        // Compute the length, if possible.
        if (parts.isEmpty())
        {
            return onlyBoundary.remaining();
        }
        else
        {
            long result = 0;
            for (int i = 0; i < parts.size(); ++i)
            {
                result += (i == 0) ? firstBoundary.remaining() : middleBoundary.remaining();
                Part part = parts.get(i);
                long partLength = part.length;
                result += partLength;
                if (partLength < 0)
                {
                    result = -1;
                    break;
                }
            }
            if (result > 0)
                result += lastBoundary.remaining();
            return result;
        }
    }
