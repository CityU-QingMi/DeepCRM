    public XmlAppendable openTag(String tag, Map<String,String> attributes) throws IOException
    {
        _out.append(_space).append('<').append(tag);
        attributes(attributes);
        
        _out.append(">\n");
        _space=_space+SPACES.substring(0,_indent);
        _tags.push(tag);
        return this;
    }
