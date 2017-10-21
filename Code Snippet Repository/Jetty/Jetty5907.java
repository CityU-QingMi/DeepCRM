    public void processAfters (XmlParser.Node ordering)
    {
        //Process the <after> elements, look for an <others/> clause and all of the <name/> clauses
        XmlParser.Node after = ordering.get("after");
        if (after == null)
            return;
        
        Iterator<?> iter = after.iterator();
        XmlParser.Node node = null;
        while (iter.hasNext())
        {
            Object o = iter.next();
            if (!(o instanceof XmlParser.Node)) continue;
            node = (XmlParser.Node) o;
            if (node.getTag().equalsIgnoreCase("others"))
            {
                if (_otherType != OtherType.None)
                    throw new IllegalStateException("Duplicate <other> clause detected in "+_xml.getURI());

                _otherType = OtherType.After;

            }
            else if (node.getTag().equalsIgnoreCase("name"))
                _afters.add(node.toString(false,true));
        }
    }
