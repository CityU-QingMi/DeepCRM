    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getSimpleName());
        str.append("@").append(Integer.toHexString(hashCode()));
        str.append("[").append(state);
        str.append(',');
        if (!inputAvailable)
        {
            str.append('!');
        }
        str.append("in,");
        if (!outputAvailable)
        {
            str.append('!');
        }
        str.append("out");
        if ((state == ConnectionState.CLOSED) || (state == ConnectionState.CLOSING))
        {
            CloseInfo ci = finalClose.get();
            if (ci != null)
            {
                str.append(",finalClose=").append(ci);
            }
            else
            {
                str.append(",close=").append(closeInfo);
            }
            str.append(",clean=").append(cleanClose);
            str.append(",closeSource=").append(closeHandshakeSource);
        }
        str.append(']');
        return str.toString();
    }
