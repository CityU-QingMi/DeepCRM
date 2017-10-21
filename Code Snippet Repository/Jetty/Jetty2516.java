    private void addContextParamFromAttribute(XmlAppendable out, String attribute, AttributeNormalizer normalizer) throws IOException
    {
        Object o = _webApp.getAttribute(attribute);
        if (o == null)
            return;
        
        Collection<?> c =  (o instanceof Collection)? (Collection<?>)o:Collections.singletonList(o);
        StringBuilder v=new StringBuilder();
        for (Object i:c)
        {
            if (i!=null)
            {
                if (v.length()>0)
                    v.append(",\n    ");
                else
                    v.append("\n    ");
                QuotedStringTokenizer.quote(v,normalizer.normalize(i));
            }
        }
        out.openTag("context-param")
        .tag("param-name",attribute)
        .tagCDATA("param-value",v.toString())
        .closeTag();  
        
    }
