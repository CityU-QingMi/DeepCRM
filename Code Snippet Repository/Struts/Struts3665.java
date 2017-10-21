    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (age != contact.age) return false;
        if (important != contact.important) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;

        return true;
    }
