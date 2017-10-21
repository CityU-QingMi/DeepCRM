    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Generator[");
        builder.append(behavior);
        if (validating)
        {
            builder.append(",validating");
        }
        if (isRsv1InUse())
        {
            builder.append(",+rsv1");
        }
        if (isRsv2InUse())
        {
            builder.append(",+rsv2");
        }
        if (isRsv3InUse())
        {
            builder.append(",+rsv3");
        }
        builder.append("]");
        return builder.toString();
    }
