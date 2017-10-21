    @Override
    protected void fileAdded(String filename) throws Exception
    {
        File file = new File(filename);
        if (!file.exists())
            return;

        //is the file that was added a directory? 
        if (file.isDirectory())
        {
            //is there a .xml file of the same name?
            if (exists(file.getName()+".xml")||exists(file.getName()+".XML"))
                return; //assume we will get added events for the xml file

            //is there .war file of the same name?
            if (exists(file.getName()+".war")||exists(file.getName()+".WAR"))
                return; //assume we will get added events for the war file

            super.fileAdded(filename);
            return;
        }


        //is the file that was added a .war file?
        String lowname = file.getName().toLowerCase(Locale.ENGLISH);
        if (lowname.endsWith(".war"))
        {
            String name = file.getName();
            String base=name.substring(0,name.length()-4);
            //is there a .xml file of the same name?
            if (exists(base+".xml")||exists(base+".XML")) 
                return; //ignore it as we should get addition of the xml file

            super.fileAdded(filename);
            return;
        }

        //is the file that was added an .xml file?
        if (lowname.endsWith(".xml"))
            super.fileAdded(filename);
    }
