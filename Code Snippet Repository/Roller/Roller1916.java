    public RuntimeConfigDefs unmarshall(InputStream instream) 
        throws IOException, JDOMException {
        
        if(instream == null) {
            throw new IOException("InputStream is null!");
        }
        
        RuntimeConfigDefs configs = new RuntimeConfigDefs();
        
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(instream);
        
        Element root = doc.getRootElement();
        List<Element> configdefs = root.getChildren("config-def");
        for (Element e : configdefs) {
            configs.addConfigDef(this.elementToConfigDef(e));
        }
        
        return configs;
    }
