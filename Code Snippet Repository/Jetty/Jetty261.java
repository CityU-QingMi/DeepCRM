    @Override
    public String toString()
    {
        return String.format("%s@%x - has=%b,last=%b,consumed=%b,buffer=%s",
                getClass().getSimpleName(),
                hashCode(),
                hasContent(),
                isLast(),
                isConsumed(),
                BufferUtil.toDetailString(getContent()));
    }
