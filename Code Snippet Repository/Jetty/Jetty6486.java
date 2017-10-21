    public CloseInfo(int statusCode, String reason)
    {
        this.statusCode = statusCode;
        if (reason != null)
        {
            byte[] utf8Bytes = reason.getBytes(StandardCharsets.UTF_8);
            if (utf8Bytes.length > CloseStatus.MAX_REASON_PHRASE)
            {
                this.reasonBytes = new byte[CloseStatus.MAX_REASON_PHRASE];
                System.arraycopy(utf8Bytes,0,this.reasonBytes,0,CloseStatus.MAX_REASON_PHRASE);
            }
            else
            {
                this.reasonBytes = utf8Bytes;
            }
        }
    }
