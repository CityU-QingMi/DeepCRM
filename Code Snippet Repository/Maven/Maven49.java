    public String toString()
    {
        if ( recommendedVersion != null )
        {
            return recommendedVersion.toString();
        }
        else
        {
            StringBuilder buf = new StringBuilder();
            for ( Iterator<Restriction> i = restrictions.iterator(); i.hasNext(); )
            {
                Restriction r = i.next();

                buf.append( r.toString() );

                if ( i.hasNext() )
                {
                    buf.append( ',' );
                }
            }
            return buf.toString();
        }
    }
