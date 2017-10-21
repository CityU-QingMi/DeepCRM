    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        List<String> system_classes=null;
        if (_systemClasses!=null)
        {
            system_classes=new ArrayList<>(_systemClasses);
            Collections.sort(system_classes);
        }
        
        List<String> server_classes=null;
        if (_serverClasses!=null)
        {
            server_classes=new ArrayList<>(_serverClasses);
            Collections.sort(server_classes);
        }
        
        dumpBeans(out,indent,
            Collections.singletonList(new ClassLoaderDump(getClassLoader())),
            Collections.singletonList(new DumpableCollection("Systemclasses "+this,system_classes)),
            Collections.singletonList(new DumpableCollection("Serverclasses "+this,server_classes)),
            Collections.singletonList(new DumpableCollection("Configurations "+this,_configurations)),
            Collections.singletonList(new DumpableCollection("Handler attributes "+this,((AttributesMap)getAttributes()).getAttributeEntrySet())),
            Collections.singletonList(new DumpableCollection("Context attributes "+this,((Context)getServletContext()).getAttributeEntrySet())),
            Collections.singletonList(new DumpableCollection("Initparams "+this,getInitParams().entrySet()))
            );
    }
