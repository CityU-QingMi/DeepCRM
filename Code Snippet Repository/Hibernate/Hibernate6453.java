   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      }
      if (obj == null || !(obj instanceof CustomerInventoryTwo)) {
         return false;
      }
      if (this.id == ((CustomerInventoryTwo) obj).id) {
         return true;
      }
      if (this.id != null && ((CustomerInventoryTwo) obj).id == null) {
         return false;
      }
      if (this.id == null && ((CustomerInventoryTwo) obj).id != null) {
         return false;
      }

      return this.id.equals(((CustomerInventoryTwo) obj).id);
   }
