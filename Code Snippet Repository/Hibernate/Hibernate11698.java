   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Address address = (Address) o;

      // inhabitants must not be in the comparison since we would end up in infinite recursion
      if (id != address.id) return false;
      if (streetNumber != address.streetNumber) return false;
      if (version != address.version) return false;
      if (cityName != null ? !cityName.equals(address.cityName) : address.cityName != null)
         return false;
      if (countryName != null ? !countryName.equals(address.countryName) : address.countryName != null)
         return false;
      if (streetName != null ? !streetName.equals(address.streetName) : address.streetName != null)
         return false;
      if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null)
         return false;

      return true;
   }
