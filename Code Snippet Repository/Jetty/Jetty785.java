    @Override
    protected void fileRemoved(String filename) throws Exception
    { 
        File file = new File(filename);

        //is the file that was removed a directory? 
        if (file.isDirectory())
        {
            //is there a .xml file of the same name?
            if (exists(file.getName()+".xml")||exists(file.getName()+".XML"))
                return; //assume we will get removed events for the xml file

            //is there .war file of the same name?
            if (exists(file.getName()+".war")||exists(file.getName()+".WAR"))
                return; //assume we will get removed events for the war file

            super.fileRemoved(filename);
            return;
        }
  
        //is the file that was removed a .war file?
        String lowname = file.getName().toLowerCase(Locale.ENGLISH);
        if (lowname.endsWith(".war"))
        {
            //is there a .xml file of the same name?
            String name = file.getName();
            String base=name.substring(0,name.length()-4);
            if (exists(base+".xml")||exists(base+".XML"))
                return; //ignore it as we should get removal of the xml file

            super.fileRemoved(filename);
            return;
        }

        //is the file that was removed an .xml file?
        if (lowname.endsWith(".xml"))
            super.fileRemoved(filename);
    }
