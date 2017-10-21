   public Address(int streetNumber, String streetName, String cityName, String countryName) {
      this.streetNumber = streetNumber;
      this.streetName = streetName;
      this.cityName = cityName;
      this.countryName = countryName;
      this.zipCode = null;
      this.inhabitants = new HashSet<Person>();
      this.id = 0;
      this.version = 0;
   }
