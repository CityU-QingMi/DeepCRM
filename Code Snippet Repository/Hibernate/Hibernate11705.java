   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Person person = (Person) o;

      if (checked != person.checked) return false;
      if (id != person.id) return false;
      if (version != person.version) return false;
      if (address != null ? !address.equals(person.address) : person.address != null)
         return false;
      if (birthDate != null ? !birthDate.equals(person.birthDate) : person.birthDate != null)
         return false;
      if (family != null ? !family.equals(person.family) : person.family != null)
         return false;
      if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null)
         return false;

      return true;
   }
