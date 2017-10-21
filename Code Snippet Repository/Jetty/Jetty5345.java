    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("EPC ");
        try (Lock locked = _locker.lock())
        {
            builder.append(_idle ? "Idle/" : "");
            builder.append(_producing ? "Prod/" : "");
            builder.append(_pending ? "Pend/" : "");
            builder.append(_execute ? "Exec/" : "");
        }
        builder.append(_producer);
        return builder.toString();
    }
