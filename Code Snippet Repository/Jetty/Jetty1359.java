    public QuotedQualityCSV(String[] preferredOrder)
    {
        this((s) -> {
            for (int i=0;i<preferredOrder.length;++i)
                if (preferredOrder[i].equals(s))
                    return preferredOrder.length-i;

            if ("*".equals(s))
                return preferredOrder.length;

            return MIN_VALUE;
        });
    }
