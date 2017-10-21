    public XmlAppendable closeTag() throws IOException
    {
        if (_tags.isEmpty())
            throw new IllegalStateException("Tags closed");
        String tag=_tags.pop();
        _space=_space.substring(0,_space.length()-_indent);
        _out.append(_space).append("</").append(tag).append(">\n");
        if (_tags.isEmpty() && _out instanceof Closeable)
            ((Closeable)_out).close();
        return this;
    }
