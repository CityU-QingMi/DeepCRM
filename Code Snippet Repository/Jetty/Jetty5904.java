    public void processName ()
    {
        XmlParser.Node root = getRoot();
        XmlParser.Node nameNode = root.get("name");
        _name = NAMELESS+(_counter++);
        if (nameNode != null)
        {
            String tmp = nameNode.toString(false,true);
            if (tmp!=null && tmp.length()>0)
                _name = tmp;
        }
    }
