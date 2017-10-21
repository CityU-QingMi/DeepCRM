    @Override
    public void processOrdering ()
    {
        //Process a fragment jar's web-fragment.xml<ordering> elements
        XmlParser.Node root = getRoot();       
        
        XmlParser.Node ordering = root.get("ordering");
        if (ordering == null)
            return; //No ordering for this fragment
        
        _isOrdered = true;
   
        processBefores(ordering);
        processAfters(ordering);
    }
