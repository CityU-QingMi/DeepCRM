    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Template template = (Template) o;

        if (dir != null ? !dir.equals(template.dir) : template.dir != null) return false;
        if (name != null ? !name.equals(template.name) : template.name != null) return false;
        if (theme != null ? !theme.equals(template.theme) : template.theme != null) return false;

        return true;
    }
