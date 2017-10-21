   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Family family = (Family) o;

      // members must not be in the comparison since we would end up in infinite recursive call
      if (id != family.id) return false;
      if (version != family.version) return false;
      if (name != null ? !name.equals(family.name) : family.name != null)
         return false;
      if (secondName != null ? !secondName.equals(family.secondName) : family.secondName != null)
         return false;

      return true;
   }
