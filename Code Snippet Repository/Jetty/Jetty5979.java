    public void processOrdering ()
    {
        //Process the web.xml's optional <absolute-ordering> element
        XmlParser.Node ordering = _root.get("absolute-ordering");
        if (ordering == null)
           return;

        _isOrdered = true;
        //If an absolute-ordering was already set, then ignore it in favor of this new one
       // _processor.setOrdering(new AbsoluteOrdering());

        Iterator<Object> iter = ordering.iterator();
        XmlParser.Node node = null;
        while (iter.hasNext())
        {
            Object o = iter.next();
            if (!(o instanceof XmlParser.Node)) continue;
            node = (XmlParser.Node) o;

            if (node.getTag().equalsIgnoreCase("others"))
                //((AbsoluteOrdering)_processor.getOrdering()).addOthers();
                _ordering.add("others");
            else if (node.getTag().equalsIgnoreCase("name"))
                //((AbsoluteOrdering)_processor.getOrdering()).add(node.toString(false,true));
                _ordering.add(node.toString(false,true));
        }
    }
