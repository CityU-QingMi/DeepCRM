   public void setAddress(Address address) {
      // To skip Hibernate BUG with access.PROPERTY : the rest should be done in DAO
      //		this.address = address;
      // Hibernate BUG : if we update the relation on 2 sides
      if (this.address != address) {
         if (this.address != null) this.address.remInhabitant(this);
         this.address = address;
         if (this.address != null) this.address.addInhabitant(this);
      }
   }
