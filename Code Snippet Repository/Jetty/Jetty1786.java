    public Enumeration<? extends Principal> members()
    {

        class MembersEnumeration implements Enumeration<Principal>
        {
            private Iterator<? extends Principal> itor;
            
            public MembersEnumeration (Iterator<? extends Principal> itor)
            {
                this.itor = itor;
            }
            
            public boolean hasMoreElements ()
            {
                return this.itor.hasNext();
            }


            public Principal nextElement ()
            {
                return this.itor.next();
            }
            
        }

        return new MembersEnumeration (_members.iterator());
    }
