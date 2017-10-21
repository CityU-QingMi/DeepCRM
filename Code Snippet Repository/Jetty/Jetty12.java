    protected String restURL(String item) 
    {
        try
        {
            return ("http://open.api.ebay.com/shopping?MaxEntries=3&appid=" + _appid + 
                    "&version=573&siteid=0&callname=FindItems&responseencoding=JSON&QueryKeywords=" + 
                    URLEncoder.encode(item,"UTF-8"));
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
