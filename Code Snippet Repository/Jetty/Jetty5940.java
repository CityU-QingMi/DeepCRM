    public void addWelcomeFiles(WebAppContext context, XmlParser.Node node)
    {
        Iterator<XmlParser.Node> iter = node.iterator("welcome-file");
        while (iter.hasNext())
        {
            XmlParser.Node indexNode = (XmlParser.Node) iter.next();
            String welcome = indexNode.toString(false, true);

            //Servlet Spec 3.0 p. 74 welcome files are additive
            if (welcome != null && welcome.trim().length() > 0)
               context.setWelcomeFiles((String[])ArrayUtil.addToArray(context.getWelcomeFiles(),welcome,String.class));
        }
    }
