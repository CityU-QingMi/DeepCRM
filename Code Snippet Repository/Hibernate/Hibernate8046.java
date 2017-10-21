        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person )) return false;

            Person person = (Person) o;

            return id != null ? id.equals(person.id) : person.id == null;

        }
