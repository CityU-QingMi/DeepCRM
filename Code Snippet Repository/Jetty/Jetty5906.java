    public void processBefores (XmlParser.Node ordering)
    {
        //Process the <before> elements, looking for an <others/> clause and all of the <name> clauses
        XmlParser.Node before = ordering.get("before");
        if (before == null)
            return;

        Iterator<?> iter = before.iterator();
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

                _otherType = OtherType.Before;
            }
            else if (node.getTag().equalsIgnoreCase("name"))
                _befores.add(node.toString(false,true));
        }
    }
