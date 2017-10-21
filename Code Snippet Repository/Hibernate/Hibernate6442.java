     @Override
     public boolean equals(Object other) {
       if (other == null || other.getClass() != this.getClass()) {
         return false;
       }
       if (other == this) {
         return true;
       }
       return id.equals(((Item) other).id);
     }
