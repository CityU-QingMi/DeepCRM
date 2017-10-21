    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(super.toString());
        str.append('[').append(_statusCode);
        str.append('>').append(_location);
        str.append(']');
        return str.toString();
    }
