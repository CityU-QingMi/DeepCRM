   protected Address() {
      this.streetNumber = 0;
      this.streetName = null;
      this.cityName = null;
      this.countryName = null;
      this.zipCode = null;
      this.inhabitants = new HashSet<Person>();
      this.id = 0;
      this.version = 0;
   }
