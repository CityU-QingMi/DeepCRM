   @Override
   public boolean equals(Object o) 
   {
       if ( this == o ) {
           return true;
       }
       if ( o == null || getClass() != o.getClass() ) 
       {
           return false;
       }

       MultiplePK multiplePK = (MultiplePK) o;

       return id1.equals( multiplePK.id1 )
               && id2.equals( multiplePK.id2 )
               && id3.equals( multiplePK.id3);
   }
