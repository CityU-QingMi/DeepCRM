    public static String getDefaultMimeByExtension(String filename)
    {
        String type=null;

        if (filename!=null)
        {
            int i=-1;
            while(type==null)
            {
                i=filename.indexOf(".",i+1);

                if (i<0 || i>=filename.length())
                    break;

                String ext=StringUtil.asciiToLowerCase(filename.substring(i+1));
                if (type==null)
                    type=__dftMimeMap.get(ext);
            }
        }

        if (type==null)
        {
            if (type==null)
                type=__dftMimeMap.get("*");
        }

        return type;
    }
