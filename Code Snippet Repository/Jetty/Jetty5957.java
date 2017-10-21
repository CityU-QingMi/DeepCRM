    @Override
    public String toString()
    {
        if (_war!=null)
        {
            String war=_war;
            if (war.indexOf("/webapps/")>=0)
                war=war.substring(war.indexOf("/webapps/")+8);
            return super.toString()+"{"+war+"}";
        }
        return super.toString();
    }
