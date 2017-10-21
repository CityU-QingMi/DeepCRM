    public static TagHandlerPool getTagHandlerPool( ServletConfig config) {
        TagHandlerPool result=null;

        String tpClassName=getOption( config, OPTION_TAGPOOL, null);
        if( tpClassName != null ) {
            try {
                Class c=Class.forName( tpClassName );
                result=(TagHandlerPool)c.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                result=null;
            }
        }
        if( result==null ) result=new TagHandlerPool();
        result.init(config);

        return result;
    }
