   public Person(String firstName, Family family) {
      this.firstName = firstName;
      this.family = family;
      this.birthDate = null;
      this.address = null;
      this.checked = false;
      this.id = 0;
      this.version = 0;
      this.family.addMember(this);
   }
