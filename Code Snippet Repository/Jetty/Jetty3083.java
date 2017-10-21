    @Override
    public String dump()
    {
        StringBuilder buf = new StringBuilder();

        buf.append(toString());
        buf.append(" WHITELIST:\n");
        dump(buf, _white);
        buf.append(toString());
        buf.append(" BLACKLIST:\n");
        dump(buf, _black);

        return buf.toString();
    }
