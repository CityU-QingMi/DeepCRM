    public String tryConvertPayload()
    {
        if (payload == null || payload.length == 0)
            return "";
        try
        {
            return new String(payload, StandardCharsets.UTF_8);
        }
        catch (Throwable x)
        {
            return "";
        }
    }
