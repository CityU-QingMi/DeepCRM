   @Override
   public int hashCode() {
      int result = streetNumber;
      result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
      result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
      result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
      result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
      result = 31 * result + id;
      result = 31 * result + version;
      return result;
   }
