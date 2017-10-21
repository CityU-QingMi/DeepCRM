    @Deprecated
    public static String trimMaxReasonLength(String reason)
    {
        if (reason == null)
        {
            return null;
        }

        byte[] reasonBytes = reason.getBytes(StandardCharsets.UTF_8);
        if (reasonBytes.length > MAX_REASON_PHRASE)
        {
            byte[] trimmed = new byte[MAX_REASON_PHRASE];
            System.arraycopy(reasonBytes,0,trimmed,0,MAX_REASON_PHRASE);
            return new String(trimmed,StandardCharsets.UTF_8);
        }
        
        return reason;
    }
