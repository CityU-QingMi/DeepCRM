    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (specialPrice != null ? !specialPrice.equals(item.specialPrice) : item.specialPrice != null) return false;

        return true;
    }
