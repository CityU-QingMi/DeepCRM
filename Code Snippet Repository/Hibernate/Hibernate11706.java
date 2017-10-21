   @Override
   public int hashCode() {
      int result = firstName != null ? firstName.hashCode() : 0;
      result = 31 * result + (family != null ? family.hashCode() : 0);
      result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
      result = 31 * result + (address != null ? address.hashCode() : 0);
      result = 31 * result + (checked ? 1 : 0);
      result = 31 * result + id;
      result = 31 * result + version;
      return result;
   }
